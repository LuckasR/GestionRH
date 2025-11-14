package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Calendrier_entrepriseService {
    List<Calendrier_entreprise> getAll();
    Calendrier_entreprise getById(Integer id);
    void save(Calendrier_entreprise obj);
    void delete(Integer id);
}