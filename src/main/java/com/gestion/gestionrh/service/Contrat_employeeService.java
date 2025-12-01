package com.gestion.gestionrh.service;

import java.util.List;

import com.gestion.gestionrh.model.Contrat_employee;

public interface Contrat_employeeService {
    List<Contrat_employee> getAll();
    Contrat_employee getById(Integer id);
    void save(Contrat_employee obj);
    void delete(Integer id);

    List<Contrat_employee> getContratsFinissantAujourdhui();
    List<Contrat_employee> getContratsTermines();
}