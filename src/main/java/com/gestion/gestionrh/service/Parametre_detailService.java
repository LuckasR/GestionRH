package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.Parametre_detail;
import java.util.List;

public interface Parametre_detailService {
    List<Parametre_detail> getAll();
    Parametre_detail getById(Integer id);
    void save(Parametre_detail obj);
    void delete(Integer id);    
}
