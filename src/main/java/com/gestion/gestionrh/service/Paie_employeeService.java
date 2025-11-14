package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Paie_employeeService {
    List<Paie_employee> getAll();
    Paie_employee getById(Integer id);
    void save(Paie_employee obj);
    void delete(Integer id);
}