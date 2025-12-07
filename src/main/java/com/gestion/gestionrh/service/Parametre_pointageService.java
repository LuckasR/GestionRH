package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Parametre_pointageService {
    List<Parametre_pointage> getAll();
    Parametre_pointage getById(Integer id);
    void save(Parametre_pointage obj);
    void delete(Integer id);
}