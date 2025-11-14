package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface FiliereService {
    List<Filiere> getAll();
    Filiere getById(Integer id);
    void save(Filiere obj);
    void delete(Integer id);
}