package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_checkService {
    List<Type_check> getAll();
    Type_check getById(Integer id);
    void save(Type_check obj);
    void delete(Integer id);
}