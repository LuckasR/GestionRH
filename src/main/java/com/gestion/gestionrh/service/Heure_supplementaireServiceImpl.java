package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
@Service
public class Heure_supplementaireServiceImpl implements Heure_supplementaireService {

    @Autowired
    private Heure_supplementaireRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Heure_supplementaire> getAll() { return repo.findAll(); }
    public Heure_supplementaire getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Heure_supplementaire obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }

    public List<Heure_supplementaire> filterHeureSup(String empName,LocalDateTime dateDebut, LocalDateTime dateFin, Integer statusId,Integer adminId,Integer typeCompensationId) {
        StringBuilder queryStr = new StringBuilder("SELECT h FROM Heure_supplementaire h WHERE 1=1");

        if (empName != null && !empName.isEmpty()) {
            queryStr.append(" AND LOWER(h.employee.username) LIKE LOWER(:empName)");
        }
        if (dateDebut != null) {
            queryStr.append(" AND h.date_creation >= :dateDebut");
        }
        if (dateFin != null) {
            queryStr.append(" AND h.date_creation <= :dateFin");
        }
        if (statusId != null) {
            queryStr.append(" AND h.status_general.id = :statusId");
        }
        if (adminId != null) {
            queryStr.append(" AND h.admin.id = :adminId");
        }
        if (typeCompensationId != null) {
            queryStr.append(" AND h.type_compensation.id = :typeCompensationId");
        }

        TypedQuery<Heure_supplementaire> query = entityManager.createQuery(queryStr.toString(), Heure_supplementaire.class);

        if (empName != null && !empName.isEmpty()) {
            query.setParameter("empName", "%" + empName.trim() + "%");
        }
        if (dateDebut != null) {
            query.setParameter("dateDebut", dateDebut);
        }
        if (dateFin != null) {
            query.setParameter("dateFin", dateFin);
        }
        if (statusId != null) {
            query.setParameter("statusId", statusId);
        }
        if (adminId != null) {
            query.setParameter("adminId", adminId);
        }
        if (typeCompensationId != null) {
            query.setParameter("typeCompensationId", typeCompensationId);
        }

        return query.getResultList();
    }
}