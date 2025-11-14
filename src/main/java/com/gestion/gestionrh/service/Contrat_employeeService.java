package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Contrat_employeeService {
    List<Contrat_employee> getAll();
    Contrat_employee getById(Integer id);
    void save(Contrat_employee obj);
    void delete(Integer id);
}