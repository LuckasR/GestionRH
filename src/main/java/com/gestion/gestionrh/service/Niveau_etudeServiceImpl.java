package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Niveau_etudeServiceImpl implements Niveau_etudeService {

    @Autowired
    private Niveau_etudeRepository repo;

    public List<Niveau_etude> getAll() { return repo.findAll(); }
    public Niveau_etude getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Niveau_etude obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}