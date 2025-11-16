package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AlerteServiceImpl implements AlerteService {

    @Autowired
    private CongeRepository congeRepo;

    @Autowired
    private AbsenceRepository absenceRepo;

    @Autowired
    private Status_generalRepository statusGeneralRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private Type_notificationRepository typeNotificationRepo;

    @Override
    public void verifierCongesEnAttente() {
        // Trouver le statut "En attente"
        Status_general statutEnAttente = statusGeneralRepo.findAll().stream()
            .filter(s -> "En attente".equals(s.getName()) || "En attente de validation".equals(s.getName()))
            .findFirst()
            .orElse(null);

        if (statutEnAttente != null) {
            List<Conge> congesEnAttente = congeRepo.findByStatus_general(statutEnAttente);
            
            for (Conge conge : congesEnAttente) {
                // Vérifier si la demande est en attente depuis plus de 3 jours
                if (conge.getDate_demande() != null) {
                    long joursEnAttente = ChronoUnit.DAYS.between(conge.getDate_demande(), LocalDateTime.now());
                    
                    if (joursEnAttente >= 3) {
                        // Envoyer alerte au superviseur
                        emailService.envoyerAlerteCongeEnAttente(conge);
                        
                // Créer une notification dans le système pour le superviseur
                if (conge.getSuperviseur() != null) {
                    creerNotification(conge.getSuperviseur(), 
                        "Rappel: Demande de congé en attente depuis " + joursEnAttente + " jours",
                        "La demande de congé de " + (conge.getEmployee() != null ? conge.getEmployee().getUsername() : "") + 
                        " (Réf: " + conge.getReference_demande() + ") est en attente de validation.");
                }
                    }
                }
            }
        }
    }

    @Override
    public void verifierAbsencesRepetees() {
        List<Employee> employees = absenceRepo.findAll().stream()
            .map(Absence::getEmployee)
            .filter(e -> e != null)
            .distinct()
            .collect(Collectors.toList());

        for (Employee employee : employees) {
            List<Absence> absences = absenceRepo.findAll().stream()
                .filter(a -> a.getEmployee() != null && a.getEmployee().getId().equals(employee.getId()))
                .filter(a -> a.getDate_absence() != null && 
                    a.getDate_absence().isAfter(java.time.LocalDate.now().minusDays(30)))
                .collect(Collectors.toList());

            if (absences.size() >= 3) {
                // Envoyer alerte
                emailService.envoyerAlerteAbsenceRepetee(employee, absences.size());
                
                // Créer une notification
                creerNotification(employee,
                    "Alerte: Absences répétées",
                    "Vous avez été absent " + absences.size() + " fois au cours des 30 derniers jours.");
            }
        }
    }

    @Override
    public void envoyerAlertes() {
        verifierCongesEnAttente();
        verifierAbsencesRepetees();
    }

    private void creerNotification(Employee employee, String message, String details) {
        if (employee != null) {
            try {
                Notification notification = new Notification();
                notification.setEmployee(employee);
                
                // Trouver le type de notification "Alerte"
                Type_notification typeAlerte = null;
                if (typeNotificationRepo != null) {
                    typeAlerte = typeNotificationRepo.findAll().stream()
                        .filter(t -> t != null && ("Alerte".equals(t.getNom())))
                        .findFirst()
                        .orElse(null);
                }
                
                notification.setType_notification(typeAlerte);
                notification.setMessage(message + ": " + details);
                notification.setEst_lu(false);
                notification.setDate_creation(LocalDateTime.now());
                
                notificationService.save(notification);
            } catch (Exception e) {
                System.err.println("Erreur lors de la création de la notification: " + e.getMessage());
            }
        }
    }

    // Exécuter les alertes tous les jours à 9h00
    @Scheduled(cron = "0 0 9 * * ?")
    public void executerAlertesQuotidiennes() {
        envoyerAlertes();
    }
}

