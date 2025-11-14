package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Siege_entrepriseService {
    List<Siege_entreprise> getAll();
    Siege_entreprise getById(Integer id);
    void save(Siege_entreprise obj);
    void delete(Integer id);
}