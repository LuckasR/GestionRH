package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Solde_congeService {
    List<Solde_conge> getAll();
    Solde_conge getById(Integer id);
    void save(Solde_conge obj);
    void delete(Integer id);
    void calculerSoldeConge(Employee employee, Integer annee);
    void mettreAJourSoldeConge(Conge conge);
    List<Solde_conge> getSoldesByEmployee(Employee employee);
}