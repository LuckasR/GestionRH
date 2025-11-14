package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface AbsenceService {
    List<Absence> getAll();
    Absence getById(Integer id);
    void save(Absence obj);
    void delete(Integer id);
}