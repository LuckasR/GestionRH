package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;
import java.time.*;
public interface Heure_supplementaireService {
    List<Heure_supplementaire> getAll();
    Heure_supplementaire getById(Integer id);
    void save(Heure_supplementaire obj);
    void delete(Integer id);
    List<Heure_supplementaire> filterHeureSup(String empName,LocalDateTime dateDebut, LocalDateTime dateFin, Integer statusId,Integer adminId,Integer typeCompensationId);
}