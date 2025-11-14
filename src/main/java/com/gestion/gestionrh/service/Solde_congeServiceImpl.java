package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Solde_congeServiceImpl implements Solde_congeService {

    @Autowired
    private Solde_congeRepository repo;

    public List<Solde_conge> getAll() { return repo.findAll(); }
    public Solde_conge getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Solde_conge obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}