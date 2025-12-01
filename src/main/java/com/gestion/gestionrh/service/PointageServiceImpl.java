package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointageServiceImpl implements PointageService {

    @Autowired
    private PointageRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Pointage> getAll() { return repo.findAll(); }
    public Pointage getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Pointage obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
    public List<Pointage> filterPointages(String empName, LocalDate date_debut, LocalDate date_fin, Integer methodeId, LocalTime heure_arrivee, LocalTime heure_depart) {
        StringBuilder queryStr = new StringBuilder("SELECT p FROM Pointage p WHERE 1=1");

        if (empName != null && !empName.isEmpty()) {
            queryStr.append(" AND p.employee.username LIKE :empName");
        }
        if (date_debut != null) {
            queryStr.append(" AND p.date_pointage >= :date_debut");
        }
        if (date_fin != null) {
            queryStr.append(" AND p.date_pointage <= :date_fin");
        }
        if (methodeId != null) {
            queryStr.append(" AND p.methode.id = :methodeId");
        }
        if (heure_arrivee != null) {
            queryStr.append(" AND p.heure_arrivee >= :heure_arrivee");
        }
        if (heure_depart != null) {
            queryStr.append(" AND p.heure_depart <= :heure_depart");
        }

        TypedQuery<Pointage> query = entityManager.createQuery(queryStr.toString(), Pointage.class);

        if (empName != null && !empName.isEmpty()) {
            query.setParameter("empName", "%" + empName.trim() + "%");
        }
        if (date_debut != null) {
            query.setParameter("date_debut", date_debut);
        }
        if (date_fin != null) {
            query.setParameter("date_fin", date_fin);
        }
        if (methodeId != null) {
            query.setParameter("methodeId", methodeId);
        }
        if (heure_arrivee != null) {
            query.setParameter("heure_arrivee", heure_arrivee.withSecond(0));
        }
        if (heure_depart != null) {
            query.setParameter("heure_depart", heure_depart.withSecond(0));
        }

        return query.getResultList();
    }
    
}