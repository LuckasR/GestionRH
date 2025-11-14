package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_changementService {
    List<Type_changement> getAll();
    Type_changement getById(Integer id);
    void save(Type_changement obj);
    void delete(Integer id);
}