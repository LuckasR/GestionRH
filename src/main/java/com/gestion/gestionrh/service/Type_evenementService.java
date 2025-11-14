package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_evenementService {
    List<Type_evenement> getAll();
    Type_evenement getById(Integer id);
    void save(Type_evenement obj);
    void delete(Integer id);
}