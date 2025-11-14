package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Information_employeeService {
    List<Information_employee> getAll();
    Information_employee getById(Integer id);
    void save(Information_employee obj);
    void delete(Integer id);
}