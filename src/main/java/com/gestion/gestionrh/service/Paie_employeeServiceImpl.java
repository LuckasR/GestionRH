package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Paie_employeeServiceImpl implements Paie_employeeService {

    @Autowired
    private Paie_employeeRepository repo;

    public List<Paie_employee> getAll() { return repo.findAll(); }
    public Paie_employee getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Paie_employee obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}