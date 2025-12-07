package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Solde_congeServiceImpl implements Solde_congeService {

    @Autowired
    private Solde_congeRepository repo;

    @Autowired
    private Type_congeRepository typeCongeRepo;

    @Autowired
    private CongeRepository congeRepo;

    public List<Solde_conge> getAll() { return repo.findAll(); }
    public Solde_conge getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Solde_conge obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    @Transactional
    public void calculerSoldeConge(Employee employee, Integer annee) {
        List<Type_conge> typesConge = typeCongeRepo.findAll();
        
        for (Type_conge typeConge : typesConge) {
            Solde_conge solde = repo.findByEmployeeAndType_congeAndAnnee(employee, typeConge, annee);
            
            // Calculer les jours acquis selon les droits du type de congé
            // Les jours acquis sont définis dans le type de congé (congés payés, maladie, maternité, exceptionnels)
            Integer joursAcquis = typeConge.getJours_attribues_annuels() != null ? typeConge.getJours_attribues_annuels() : 0;
            
            if (solde == null) {
                // Créer un nouveau solde
                solde = new Solde_conge();
                solde.setEmployee(employee);
                solde.setType_conge(typeConge);
                solde.setAnnee(annee);
                solde.setJours_acquis(joursAcquis);
                solde.setJours_pris(0);
                solde.setJours_restants(joursAcquis);
            } else {
                // Mettre à jour le solde existant
                solde.setJours_acquis(joursAcquis);
            }
            
            // Calculer les jours pris (congés approuvés pour ce type et cette année)
            List<Conge> congesApprouves = congeRepo.findByEmployee(employee);
            int joursPris = 0;
            for (Conge conge : congesApprouves) {
                if (conge.getType_conge() != null 
                    && conge.getType_conge().getId().equals(typeConge.getId()) 
                    && conge.getDate_debut() != null 
                    && conge.getDate_debut().getYear() == annee
                    && conge.getStatus_general() != null
                    && ("Approuvé".equals(conge.getStatus_general().getName()) 
                        || "Approuve".equals(conge.getStatus_general().getName()))) {
                    joursPris += conge.getNb_jours() != null ? conge.getNb_jours() : 0;
                }
            }
            
            solde.setJours_pris(joursPris);
            solde.setJours_restants(Math.max(0, joursAcquis - joursPris)); // Éviter les valeurs négatives
            solde.setDate_maj(LocalDateTime.now());
            
            repo.save(solde);
        }
    }

    @Override
    @Transactional
    public void mettreAJourSoldeConge(Conge conge) {
        if (conge.getEmployee() != null && conge.getType_conge() != null && conge.getDate_debut() != null) {
            Integer annee = conge.getDate_debut().getYear();
            calculerSoldeConge(conge.getEmployee(), annee);
        }
    }

    @Override
    public List<Solde_conge> getSoldesByEmployee(Employee employee) {
        return repo.findByEmployee(employee);
    }
}