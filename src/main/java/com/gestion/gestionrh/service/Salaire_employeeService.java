package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Salaire_employeeService {
    List<Salaire_employee> getAll();
    Salaire_employee getById(Integer id);
    void save(Salaire_employee obj);
    void delete(Integer id);
}