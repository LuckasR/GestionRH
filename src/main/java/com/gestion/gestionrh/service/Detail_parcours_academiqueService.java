package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Detail_parcours_academiqueService {
    List<Detail_parcours_academique> getAll();
    Detail_parcours_academique getById(Integer id);
    void save(Detail_parcours_academique obj);
    void delete(Integer id);
}