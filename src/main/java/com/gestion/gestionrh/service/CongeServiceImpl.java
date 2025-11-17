package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CongeServiceImpl implements CongeService {

    @Autowired
    private CongeRepository repo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private Status_generalRepository statusGeneralRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private Calendrier_entrepriseRepository calendrierRepo;

    @Autowired
    private Evenement_calendrierRepository evenementCalendrierRepo;

    @Autowired
    private Type_evenementRepository typeEvenementRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Solde_congeService soldeCongeService;

    public List<Conge> getAll() { return repo.findAll(); }
    public Conge getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Conge obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    @Transactional
    public void integrerCongeDansCalendrier(Conge conge) {
        if (conge.getStatus_general() != null && 
            "Approuvé".equals(conge.getStatus_general().getName()) &&
            conge.getDate_debut() != null && conge.getDate_fin() != null) {
            
            // Trouver ou créer le calendrier de l'année
            Integer annee = conge.getDate_debut().getYear();
            Calendrier_entreprise calendrier = calendrierRepo.findAll().stream()
                .filter(c -> c.getAnnee() != null && c.getAnnee().equals(annee))
                .findFirst()
                .orElse(null);
            
            if (calendrier == null) {
                Calendrier_entreprise nouveauCalendrier = new Calendrier_entreprise();
                nouveauCalendrier.setTitre("Calendrier " + annee);
                nouveauCalendrier.setDescription("Calendrier des congés et événements de l'entreprise pour l'année " + annee);
                nouveauCalendrier.setAnnee(annee);
                nouveauCalendrier.setDate_creation(LocalDateTime.now());
                calendrier = calendrierRepo.save(nouveauCalendrier);
            }

            final Calendrier_entreprise calendrierFinal = calendrier;
            // Vérifier si l'événement existe déjà
            boolean existeDeja = evenementCalendrierRepo.findAll().stream()
                .anyMatch(e -> e.getEmployee() != null && e.getEmployee().getId().equals(conge.getEmployee().getId())
                    && e.getDate_debut() != null && e.getDate_debut().equals(conge.getDate_debut())
                    && e.getDate_fin() != null && e.getDate_fin().equals(conge.getDate_fin())
                    && e.getCalendrier_entreprise() != null && e.getCalendrier_entreprise().getId().equals(calendrierFinal.getId()));

            if (!existeDeja) {
                Evenement_calendrier evenement = new Evenement_calendrier();
                evenement.setCalendrier_entreprise(calendrierFinal);
                evenement.setEmployee(conge.getEmployee());
                
                // Trouver le type d'événement "Congé" ou créer un par défaut
                Type_evenement typeConge = typeEvenementRepo.findAll().stream()
                    .filter(t -> t != null && ("Congé".equals(t.getNom()) || "Conge".equals(t.getNom())))
                    .findFirst()
                    .orElse(null);
                
                if (typeConge == null && !typeEvenementRepo.findAll().isEmpty()) {
                    typeConge = typeEvenementRepo.findAll().get(0);
                }
                evenement.setType_evenement(typeConge);
                
                evenement.setTitre("Congé - " + (conge.getEmployee() != null ? conge.getEmployee().getUsername() : ""));
                evenement.setDescription("Congé: " + (conge.getType_conge() != null ? conge.getType_conge().getLibelle() : "") + 
                    (conge.getCommentaire() != null ? " - " + conge.getCommentaire() : ""));
                evenement.setDate_debut(conge.getDate_debut());
                evenement.setDate_fin(conge.getDate_fin());
                evenement.setStatus_general(conge.getStatus_general());
                evenement.setCouleur("#FF9800"); // Couleur orange pour les congés
                evenement.setDate_creation(LocalDateTime.now());
                
                evenementCalendrierRepo.save(evenement);
            }
        }
    }

    @Override
    @Transactional
    public void validerParSuperviseur(Integer congeId, Integer statutId) {
        Conge conge = getById(congeId);
        if (conge != null) {
            Status_general statut = statusGeneralRepo.findById(statutId).orElse(null);

            if (statut != null) {
                // Vérifier les conflits de dates avant validation
                if ("Approuvé".equals(statut.getName()) || "Approuve".equals(statut.getName())) {
                    boolean conflit = verifierConflitDates(conge);
                    if (conflit) {
                        throw new RuntimeException("Conflit détecté : période de congé chevauche avec d'autres congés approuvés.");
                    }
                }

                conge.setStatut_validation_superviseur(statut);
                conge.setDate_validation_superviseur(LocalDateTime.now());

                // Si rejeté par le superviseur, mettre le statut général à "Rejeté"
                if ("Rejeté".equals(statut.getName()) || "Rejete".equals(statut.getName())) {
                    Status_general statutRejete = statusGeneralRepo.findAll().stream()
                        .filter(s -> "Rejeté".equals(s.getName()) || "Rejete".equals(s.getName()))
                        .findFirst()
                        .orElse(statut);
                    conge.setStatus_general(statutRejete);
                }

                save(conge);

                // Envoyer notification
                emailService.envoyerNotificationValidationSuperviseur(conge);
            }
        }
    }

    private boolean verifierConflitDates(Conge conge) {
        if (conge.getDate_debut() == null || conge.getDate_fin() == null) {
            return false;
        }

        return repo.findAll().stream()
            .filter(c -> !c.getId().equals(conge.getId())) // Exclure le congé actuel
            .filter(c -> c.getStatus_general() != null &&
                       ("Approuvé".equals(c.getStatus_general().getName()) ||
                        "Approuve".equals(c.getStatus_general().getName())))
            .anyMatch(c -> {
                // Vérifier si les périodes se chevauchent
                return !(conge.getDate_fin().isBefore(c.getDate_debut()) ||
                        conge.getDate_debut().isAfter(c.getDate_fin()));
            });
    }

    @Override
    @Transactional
    public void validerParRH(Integer congeId, Integer adminId, Integer statutId) {
        Conge conge = getById(congeId);
        if (conge != null) {
            Admin admin = adminRepo.findById(adminId).orElse(null);
            Status_general statut = statusGeneralRepo.findById(statutId).orElse(null);
            
            if (admin != null && statut != null) {
                conge.setAdmin(admin);
                conge.setStatus_general(statut);
                conge.setDate_validation(LocalDateTime.now());
                
                save(conge);
                
                // Intégrer dans le calendrier si approuvé
                if ("Approuvé".equals(statut.getName()) || "Approuve".equals(statut.getName())) {
                    integrerCongeDansCalendrier(conge);
                    // Mettre à jour le solde de congé
                    soldeCongeService.mettreAJourSoldeConge(conge);
                }
                
                // Envoyer notification
                emailService.envoyerNotificationValidationRH(conge);
            }
        }
    }
}