package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Heure_supplementaireServiceImpl implements Heure_supplementaireService {

    @Autowired
    private Heure_supplementaireRepository repo;

    public List<Heure_supplementaire> getAll() { return repo.findAll(); }
    public Heure_supplementaire getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Heure_supplementaire obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}