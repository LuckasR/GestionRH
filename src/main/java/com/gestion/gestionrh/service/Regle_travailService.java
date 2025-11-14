package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Regle_travailService {
    List<Regle_travail> getAll();
    Regle_travail getById(Integer id);
    void save(Regle_travail obj);
    void delete(Integer id);
}