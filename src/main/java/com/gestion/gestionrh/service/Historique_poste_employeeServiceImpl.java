package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Historique_poste_employeeServiceImpl implements Historique_poste_employeeService {

    @Autowired
    private Historique_poste_employeeRepository repo;

    public List<Historique_poste_employee> getAll() { return repo.findAll(); }
    public Historique_poste_employee getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Historique_poste_employee obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}