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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional // Keeps the connection open so Thymeleaf can load the details
    public List<Parametre_taux> getAll() {
        return repo.findAll();
    }

    public Parametre_taux getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Parametre_taux obj) {
        repo.save(obj);
    }

    public Parametre_taux saveAndGet(Parametre_taux obj) {
        return repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Parametre_taux> filter(String code, BigDecimal tauxEmpMin, BigDecimal tauxEmpMax,
            BigDecimal tauxEmployeurMin, BigDecimal tauxEmployeurMax,
            LocalDate dateDebut, LocalDate dateFin, Boolean actif) {

        System.out.println("=== DÉBUT FILTRE SERVICE (RELATIONAL) ===");

        // 1. Validate Inputs
        this.validateFilterRanges(tauxEmpMin, tauxEmpMax, tauxEmployeurMin, tauxEmployeurMax, dateDebut, dateFin);

        // 2. Start building the Query
        StringBuilder jpql = new StringBuilder();

        // CORRECTION 1: Start FROM the Parent (Parametre_taux), aliased as 'p'
        jpql.append("SELECT DISTINCT p FROM Parametre_taux p ");

        // CORRECTION 2: Join the List<ParametreDetail> named 'details' (Check your Entity!)
        jpql.append("LEFT JOIN p.details pd ");

        // CORRECTION 3: Join the Taux from the Detail
        jpql.append("LEFT JOIN pd.taux t ");

        jpql.append("WHERE 1=1 ");

        // --- ADD FILTERS ---
        // Filter by CODE
        if (code != null && !code.trim().isEmpty()) {
            jpql.append(" AND LOWER(p.code) LIKE LOWER(:code)");
        }

        // CORRECTION 4: Use 'date_application' (Matches your Parametre_taux entity)
        if (dateDebut != null) {
            jpql.append(" AND p.date_application >= :dateDebut");
        }
        if (dateFin != null) {
            jpql.append(" AND p.date_application <= :dateFin"); // Assuming logic is on start date, or check p.date_fin
        }

        // Filter by ACTIF
        if (actif != null) {
            jpql.append(" AND p.actif = :actif");
        }

        // Filter by RATES (on Taux - Using alias 't')
        if (tauxEmpMin != null) {
            jpql.append(" AND t.tauxEmploye >= :tauxEmpMin");
        }
        if (tauxEmpMax != null) {
            jpql.append(" AND t.tauxEmploye <= :tauxEmpMax");
        }
        if (tauxEmployeurMin != null) {
            jpql.append(" AND t.tauxEmployeur >= :tauxEmployeurMin");
        }
        if (tauxEmployeurMax != null) {
            jpql.append(" AND t.tauxEmployeur <= :tauxEmployeurMax");
        }

        System.out.println("Requête JPQL générée: " + jpql.toString());

        // 3. Create Query
        TypedQuery<Parametre_taux> query = entityManager.createQuery(jpql.toString(), Parametre_taux.class);

        // --- SET PARAMETERS ---
        if (code != null && !code.trim().isEmpty()) {
            query.setParameter("code", "%" + code.trim() + "%");
        }
        if (dateDebut != null) {
            query.setParameter("dateDebut", dateDebut);
        }
        if (dateFin != null) {
            query.setParameter("dateFin", dateFin);
        }
        if (actif != null) {
            query.setParameter("actif", actif);
        }
        if (tauxEmpMin != null) {
            query.setParameter("tauxEmpMin", tauxEmpMin);
        }
        if (tauxEmpMax != null) {
            query.setParameter("tauxEmpMax", tauxEmpMax);
        }
        if (tauxEmployeurMin != null) {
            query.setParameter("tauxEmployeurMin", tauxEmployeurMin);
        }
        if (tauxEmployeurMax != null) {
            query.setParameter("tauxEmployeurMax", tauxEmployeurMax);
        }

        // 4. Execute
        List<Parametre_taux> result = query.getResultList();

        System.out.println("Nombre de résultats trouvés: " + result.size());
        System.out.println("=== FIN FILTRE SERVICE ===");

        return result;
    }
}
