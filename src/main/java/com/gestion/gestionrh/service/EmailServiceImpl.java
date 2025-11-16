package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Information_employeeService informationEmployeeService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void envoyerEmail(String destinataire, String sujet, String contenu) {
        if (destinataire != null && !destinataire.isEmpty()) {
            // Simuler l'envoi d'email en loggant
            System.out.println("========================================");
            System.out.println("EMAIL SIMULÉ");
            System.out.println("À: " + destinataire);
            System.out.println("Sujet: " + sujet);
            System.out.println("Contenu:");
            System.out.println(contenu);
            System.out.println("========================================");
            
            // Optionnel: créer une notification dans le système pour simuler l'email
            // (décommenter si vous voulez stocker les "emails" dans la base de données)
            /*
            try {
                // Trouver l'employé par email
                var infoList = informationEmployeeService.getAll();
                Employee employee = null;
                for (var info : infoList) {
                    if (info.getEmail() != null && info.getEmail().equals(destinataire)) {
                        employee = info.getEmployee();
                        break;
                    }
                }
                
                if (employee != null) {
                    Notification notification = new Notification();
                    notification.setEmployee(employee);
                    notification.setMessage("📧 " + sujet + "\n\n" + contenu);
                    notification.setEst_lu(false);
                    notification.setDate_creation(LocalDateTime.now());
                    notificationService.save(notification);
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de la création de la notification: " + e.getMessage());
            }
            */
        }
    }

    @Override
    public void envoyerNotificationConge(Conge conge) {
        if (conge.getEmployee() != null) {
            String email = getEmailEmployee(conge.getEmployee());
            if (email != null) {
                String sujet = "Demande de congé soumise";
                String contenu = String.format(
                    "Bonjour,\n\n" +
                    "Votre demande de congé a été soumise avec succès.\n\n" +
                    "Détails de la demande:\n" +
                    "- Type de congé: %s\n" +
                    "- Date de début: %s\n" +
                    "- Date de fin: %s\n" +
                    "- Nombre de jours: %d\n" +
                    "- Référence: %s\n\n" +
                    "Votre demande est en attente de validation par votre supérieur hiérarchique.\n\n" +
                    "Cordialement,\n" +
                    "Service RH",
                    conge.getType_conge() != null ? conge.getType_conge().getLibelle() : "N/A",
                    conge.getDate_debut(),
                    conge.getDate_fin(),
                    conge.getNb_jours(),
                    conge.getReference_demande()
                );
                envoyerEmail(email, sujet, contenu);
            }
        }
    }

    @Override
    public void envoyerNotificationValidationSuperviseur(Conge conge) {
        if (conge.getSuperviseur() != null) {
            String email = getEmailEmployee(conge.getSuperviseur());
            if (email != null) {
                String statut = conge.getStatut_validation_superviseur() != null ? 
                    conge.getStatut_validation_superviseur().getName() : "En attente";
                String sujet = "Demande de congé à valider";
                String contenu = String.format(
                    "Bonjour,\n\n" +
                    "Une demande de congé nécessite votre validation.\n\n" +
                    "Détails de la demande:\n" +
                    "- Employé: %s\n" +
                    "- Type de congé: %s\n" +
                    "- Date de début: %s\n" +
                    "- Date de fin: %s\n" +
                    "- Nombre de jours: %d\n" +
                    "- Référence: %s\n\n" +
                    "Statut: %s\n\n" +
                    "Veuillez valider ou rejeter cette demande dans le système.\n\n" +
                    "Cordialement,\n" +
                    "Service RH",
                    conge.getEmployee() != null ? conge.getEmployee().getUsername() : "N/A",
                    conge.getType_conge() != null ? conge.getType_conge().getLibelle() : "N/A",
                    conge.getDate_debut(),
                    conge.getDate_fin(),
                    conge.getNb_jours(),
                    conge.getReference_demande(),
                    statut
                );
                envoyerEmail(email, sujet, contenu);
            }
        }
    }

    @Override
    public void envoyerNotificationValidationRH(Conge conge) {
        if (conge.getEmployee() != null) {
            String email = getEmailEmployee(conge.getEmployee());
            if (email != null) {
                String statut = conge.getStatus_general() != null ? 
                    conge.getStatus_general().getName() : "En attente";
                String sujet = "Statut de votre demande de congé";
                String contenu = String.format(
                    "Bonjour,\n\n" +
                    "Votre demande de congé a été traitée par le service RH.\n\n" +
                    "Détails de la demande:\n" +
                    "- Type de congé: %s\n" +
                    "- Date de début: %s\n" +
                    "- Date de fin: %s\n" +
                    "- Nombre de jours: %d\n" +
                    "- Référence: %s\n\n" +
                    "Statut final: %s\n\n" +
                    "Cordialement,\n" +
                    "Service RH",
                    conge.getType_conge() != null ? conge.getType_conge().getLibelle() : "N/A",
                    conge.getDate_debut(),
                    conge.getDate_fin(),
                    conge.getNb_jours(),
                    conge.getReference_demande(),
                    statut
                );
                envoyerEmail(email, sujet, contenu);
            }
        }
    }

    @Override
    public void envoyerAlerteCongeEnAttente(Conge conge) {
        if (conge.getSuperviseur() != null) {
            String email = getEmailEmployee(conge.getSuperviseur());
            if (email != null) {
                String sujet = "Rappel: Demande de congé en attente de validation";
                String contenu = String.format(
                    "Bonjour,\n\n" +
                    "Ceci est un rappel: une demande de congé est en attente de votre validation depuis plusieurs jours.\n\n" +
                    "Détails:\n" +
                    "- Employé: %s\n" +
                    "- Type de congé: %s\n" +
                    "- Date de début: %s\n" +
                    "- Référence: %s\n\n" +
                    "Veuillez traiter cette demande dans les plus brefs délais.\n\n" +
                    "Cordialement,\n" +
                    "Service RH",
                    conge.getEmployee() != null ? conge.getEmployee().getUsername() : "N/A",
                    conge.getType_conge() != null ? conge.getType_conge().getLibelle() : "N/A",
                    conge.getDate_debut(),
                    conge.getReference_demande()
                );
                envoyerEmail(email, sujet, contenu);
            }
        }
    }

    @Override
    public void envoyerAlerteAbsenceRepetee(Employee employee, int nombreAbsences) {
        String email = getEmailEmployee(employee);
        if (email != null) {
            String sujet = "Alerte: Absences répétées";
            String contenu = String.format(
                "Bonjour,\n\n" +
                "Vous avez été absent %d fois récemment.\n\n" +
                "Veuillez vous assurer que toutes vos absences sont justifiées et documentées.\n\n" +
                "Si vous avez des questions, veuillez contacter le service RH.\n\n" +
                "Cordialement,\n" +
                "Service RH",
                nombreAbsences
            );
            envoyerEmail(email, sujet, contenu);
        }
    }

    private String getEmailEmployee(Employee employee) {
        try {
            var infoList = informationEmployeeService.getAll();
            for (var info : infoList) {
                if (info.getEmployee() != null && info.getEmployee().getId().equals(employee.getId())) {
                    return info.getEmail();
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de l'email: " + e.getMessage());
        }
        return null;
    }
}

