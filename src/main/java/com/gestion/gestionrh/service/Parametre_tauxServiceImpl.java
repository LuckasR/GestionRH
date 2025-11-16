package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.math.BigDecimal;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Parametre_tauxServiceImpl implements Parametre_tauxService {

    @Autowired
    private Parametre_tauxRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public void validateFilterRanges(BigDecimal tauxEmpMin, BigDecimal tauxEmpMax,
                                     BigDecimal tauxEmployeurMin, BigDecimal tauxEmployeurMax,
                                     LocalDate dateDebut, LocalDate dateFin) {
        
        // Validation des plages de taux employé
        if (tauxEmpMin != null && tauxEmpMax != null) {
            if (tauxEmpMin.compareTo(tauxEmpMax) > 0) {
                throw new IllegalArgumentException("Le taux employé minimum ne peut pas être supérieur au taux maximum");
            }
            if (tauxEmpMin.compareTo(BigDecimal.ZERO) < 0 || tauxEmpMax.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Les taux employé doivent être positifs");
            }
        }

        // Validation des plages de taux employeur
        if (tauxEmployeurMin != null && tauxEmployeurMax != null) {
            if (tauxEmployeurMin.compareTo(tauxEmployeurMax) > 0) {
                throw new IllegalArgumentException("Le taux employeur minimum ne peut pas être supérieur au taux maximum");
            }
            if (tauxEmployeurMin.compareTo(BigDecimal.ZERO) < 0 || tauxEmployeurMax.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Les taux employeur doivent être positifs");
            }
        }

        // Validation des plages de dates
        if (dateDebut != null && dateFin != null) {
            if (dateDebut.isAfter(dateFin)) {
                throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin");
            }
        }

        // Validation des valeurs individuelles
        if (tauxEmpMin != null && tauxEmpMin.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le taux employé minimum ne peut pas être négatif");
        }
        if (tauxEmpMax != null && tauxEmpMax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le taux employé maximum ne peut pas être négatif");
        }
        if (tauxEmployeurMin != null && tauxEmployeurMin.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le taux employeur minimum ne peut pas être négatif");
        }
        if (tauxEmployeurMax != null && tauxEmployeurMax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le taux employeur maximum ne peut pas être négatif");
        }
    }

    public List<Parametre_taux> getAll() { return repo.findAll(); }
    public Parametre_taux getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Parametre_taux obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
    public List<Parametre_taux> filter(String code, BigDecimal tauxEmpMin, BigDecimal tauxEmpMax, 
                                  BigDecimal tauxEmployeurMin, BigDecimal tauxEmployeurMax, 
                                  LocalDate dateDebut, LocalDate dateFin, Boolean actif) {
    
    System.out.println("=== DÉBUT FILTRE SERVICE ===");
    System.out.println("Paramètres reçus dans service:");
    System.out.println("  code: " + code);
    System.out.println("  tauxEmployeurMin: " + tauxEmployeurMin);
    System.out.println("  tauxEmployeurMax: " + tauxEmployeurMax);
    System.out.println("  actif: " + actif);
    
    String jpql = "SELECT p FROM Parametre_taux p WHERE 1=1";
    
    this.validateFilterRanges(tauxEmpMin, tauxEmpMax, tauxEmployeurMin, tauxEmployeurMax, dateDebut, dateFin);
    
    // Construction de la requête
    if (code != null && !code.trim().isEmpty()) {
        jpql += " AND p.code LIKE :code";
        System.out.println("  → Ajout filtre code");
    }
    if (dateDebut != null) {
        jpql += " AND p.date_application >= :dateDebut";
        System.out.println("  → Ajout filtre dateDebut");
    }
    if (dateFin != null) {
        jpql += " AND p.date_application <= :dateFin";
        System.out.println("  → Ajout filtre dateFin");
    }
    if (tauxEmpMin != null) {
        jpql += " AND p.taux_employe >= :tauxEmpMin";
        System.out.println("  → Ajout filtre tauxEmpMin: " + tauxEmpMin);
    }
    if (tauxEmpMax != null) {
        jpql += " AND p.taux_employe <= :tauxEmpMax";
        System.out.println("  → Ajout filtre tauxEmpMax: " + tauxEmpMax);
    }
    if (tauxEmployeurMin != null) {
        jpql += " AND p.taux_employeur >= :tauxEmployeurMin";
        System.out.println("  → Ajout filtre tauxEmployeurMin: " + tauxEmployeurMin);
    }
    if (tauxEmployeurMax != null) {
        jpql += " AND p.taux_employeur <= :tauxEmployeurMax";
        System.out.println("  → Ajout filtre tauxEmployeurMax: " + tauxEmployeurMax);
    }
    if (actif != null) {
        jpql += " AND p.actif = :actif";
        System.out.println("  → Ajout filtre actif: " + actif);
    }

    System.out.println("Requête JPQL générée: " + jpql);

    Query query = entityManager.createQuery(jpql, Parametre_taux.class);

    // Définition des paramètres
    if (code != null && !code.trim().isEmpty()) {
        String codeValue = "%" + code.trim() + "%";
        query.setParameter("code", codeValue);
        System.out.println("  → Paramètre code: " + codeValue);
    }
    if (dateDebut != null) {
        query.setParameter("dateDebut", dateDebut);
        System.out.println("  → Paramètre dateDebut: " + dateDebut);
    }
    if (dateFin != null) {
        query.setParameter("dateFin", dateFin);
        System.out.println("  → Paramètre dateFin: " + dateFin);
    }
    if (tauxEmpMin != null) {
        query.setParameter("tauxEmpMin", tauxEmpMin);
        System.out.println("  → Paramètre tauxEmpMin: " + tauxEmpMin);
    }
    if (tauxEmpMax != null) {
        query.setParameter("tauxEmpMax", tauxEmpMax);
        System.out.println("  → Paramètre tauxEmpMax: " + tauxEmpMax);
    }
    if (tauxEmployeurMin != null) {
        query.setParameter("tauxEmployeurMin", tauxEmployeurMin);
        System.out.println("  → Paramètre tauxEmployeurMin: " + tauxEmployeurMin);
    }
    if (tauxEmployeurMax != null) {
        query.setParameter("tauxEmployeurMax", tauxEmployeurMax);
        System.out.println("  → Paramètre tauxEmployeurMax: " + tauxEmployeurMax);
    }
    if (actif != null) {
        query.setParameter("actif", actif);
        System.out.println("  → Paramètre actif: " + actif);
    }

    List<Parametre_taux> result = query.getResultList();
    System.out.println("Nombre de résultats trouvés: " + result.size());
    System.out.println("=== FIN FILTRE SERVICE ===");
    
    return result;
}

}