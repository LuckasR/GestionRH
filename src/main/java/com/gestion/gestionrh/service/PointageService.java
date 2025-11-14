package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface PointageService {
    List<Pointage> getAll();
    Pointage getById(Integer id);
    void save(Pointage obj);
    void delete(Integer id);
}