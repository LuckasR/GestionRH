package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_operation_congeService {
    List<Type_operation_conge> getAll();
    Type_operation_conge getById(Integer id);
    void save(Type_operation_conge obj);
    void delete(Integer id);
}