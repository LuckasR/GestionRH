package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;

import java.time.LocalDate;
import java.util.List;

public interface DetailPointageService {
    List<DetailPointage> getAll();
    DetailPointage getById(Integer id);
    void save(DetailPointage obj);
    void delete(Integer id);

    // Récupérer tous les détails d'un pointage
    List<DetailPointage> getByPointageId(Integer pointageId);

    // Récupérer le détail du jour d’un employé
    List<DetailPointage> getTodayDetails(Integer empId, LocalDate date);
    double getHeureSup(   Integer empId, LocalDate date , List<Horaire_travail> horaireList ) ; 
    double getHeureSup(LocalDate dateDebut, LocalDate dateFin, Integer empId, List<Horaire_travail> horaireList) ; 
}
