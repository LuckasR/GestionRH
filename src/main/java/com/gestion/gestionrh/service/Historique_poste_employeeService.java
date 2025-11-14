package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Historique_poste_employeeService {
    List<Historique_poste_employee> getAll();
    Historique_poste_employee getById(Integer id);
    void save(Historique_poste_employee obj);
    void delete(Integer id);
}