package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Historique_solde_congeService {
    List<Historique_solde_conge> getAll();
    Historique_solde_conge getById(Integer id);
    void save(Historique_solde_conge obj);
    void delete(Integer id);
}