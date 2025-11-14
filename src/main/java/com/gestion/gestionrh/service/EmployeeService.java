package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Integer id);
    void save(Employee obj);
    void delete(Integer id);
}