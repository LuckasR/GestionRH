package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Contrat_employeeServiceImpl implements Contrat_employeeService {

    @Autowired
    private Contrat_employeeRepository repo;

    public List<Contrat_employee> getAll() { return repo.findAll(); }
    public Contrat_employee getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Contrat_employee obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}