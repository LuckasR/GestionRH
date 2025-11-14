package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Assurance_social_employeeServiceImpl implements Assurance_social_employeeService {

    @Autowired
    private Assurance_social_employeeRepository repo;

    public List<Assurance_social_employee> getAll() { return repo.findAll(); }
    public Assurance_social_employee getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Assurance_social_employee obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}