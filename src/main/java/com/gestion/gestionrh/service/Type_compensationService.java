package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_compensationService {
    List<Type_compensation> getAll();
    Type_compensation getById(Integer id);
    void save(Type_compensation obj);
    void delete(Integer id);
}