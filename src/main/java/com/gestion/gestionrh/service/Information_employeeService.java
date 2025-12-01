package com.gestion.gestionrh.service;

import java.util.List;

import com.gestion.gestionrh.model.Information_employee;

public interface Information_employeeService {
    List<Information_employee> findAllByEmployeeId(Integer employeeId);
    List<Information_employee> getAll();
    Information_employee getById(Integer id);
    void save(Information_employee obj);
    void delete(Integer id);
}