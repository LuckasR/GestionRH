package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Calendrier_entrepriseServiceImpl implements Calendrier_entrepriseService {

    @Autowired
    private Calendrier_entrepriseRepository repo;

    public List<Calendrier_entreprise> getAll() { return repo.findAll(); }
    public Calendrier_entreprise getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Calendrier_entreprise obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}