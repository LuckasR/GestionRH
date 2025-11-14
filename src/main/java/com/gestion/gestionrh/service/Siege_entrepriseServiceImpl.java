package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Siege_entrepriseServiceImpl implements Siege_entrepriseService {

    @Autowired
    private Siege_entrepriseRepository repo;

    public List<Siege_entreprise> getAll() { return repo.findAll(); }
    public Siege_entreprise getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Siege_entreprise obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}