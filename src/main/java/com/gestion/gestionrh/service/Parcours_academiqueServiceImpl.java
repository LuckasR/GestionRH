package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Parcours_academiqueServiceImpl implements Parcours_academiqueService {

    @Autowired
    private Parcours_academiqueRepository repo;

    public List<Parcours_academique> getAll() { return repo.findAll(); }
    public Parcours_academique getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Parcours_academique obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}