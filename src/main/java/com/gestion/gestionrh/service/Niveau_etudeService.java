package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Niveau_etudeService {
    List<Niveau_etude> getAll();
    Niveau_etude getById(Integer id);
    void save(Niveau_etude obj);
    void delete(Integer id);
}