package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Historique_solde_congeServiceImpl implements Historique_solde_congeService {

    @Autowired
    private Historique_solde_congeRepository repo;

    public List<Historique_solde_conge> getAll() { return repo.findAll(); }
    public Historique_solde_conge getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Historique_solde_conge obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}