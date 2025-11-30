package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Horaire_travailService {
    List<Horaire_travail> getAll();
    Horaire_travail getById(Integer id);
    void save(Horaire_travail obj);
    void delete(Integer id);
}