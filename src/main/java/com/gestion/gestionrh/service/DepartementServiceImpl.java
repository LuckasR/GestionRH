package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    private DepartementRepository repo;

    public List<Departement> getAll() { return repo.findAll(); }
    public Departement getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Departement obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}