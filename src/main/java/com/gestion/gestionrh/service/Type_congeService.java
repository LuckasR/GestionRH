package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_congeService {
    List<Type_conge> getAll();
    Type_conge getById(Integer id);
    void save(Type_conge obj);
    void delete(Integer id);
}