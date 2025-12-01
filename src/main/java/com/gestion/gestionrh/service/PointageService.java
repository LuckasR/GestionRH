package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;
import java.time.*;

public interface PointageService {
    List<Pointage> getAll();
    Pointage getById(Integer id);
    void save(Pointage obj);
    void delete(Integer id);
    List<Pointage> filterPointages(String empName, LocalDate date_debut, LocalDate date_fin, Integer methodeId, LocalTime heure_arrivee, LocalTime heure_depart);
}