package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Detail_contrat_employeeService {
    List<Detail_contrat_employee> getAll();
    Detail_contrat_employee getById(Integer id);
    void save(Detail_contrat_employee obj);
    void delete(Integer id);
}