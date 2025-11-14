package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Parcours_academiqueService {
    List<Parcours_academique> getAll();
    Parcours_academique getById(Integer id);
    void save(Parcours_academique obj);
    void delete(Integer id);
}