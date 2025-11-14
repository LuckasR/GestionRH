package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_contratService {
    List<Type_contrat> getAll();
    Type_contrat getById(Integer id);
    void save(Type_contrat obj);
    void delete(Integer id);
}