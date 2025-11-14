package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface DepartementService {
    List<Departement> getAll();
    Departement getById(Integer id);
    void save(Departement obj);
    void delete(Integer id);
}