package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface CongeService {
    List<Conge> getAll();
    Conge getById(Integer id);
    void save(Conge obj);
    void delete(Integer id);
    void integrerCongeDansCalendrier(Conge conge);
    void validerParSuperviseur(Integer congeId, Integer statutId);
    void validerParRH(Integer congeId, Integer adminId, Integer statutId);
}