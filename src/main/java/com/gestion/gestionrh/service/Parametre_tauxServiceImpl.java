package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Parametre_tauxServiceImpl implements Parametre_tauxService {

    @Autowired
    private Parametre_tauxRepository repo;

    public List<Parametre_taux> getAll() { return repo.findAll(); }
    public Parametre_taux getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Parametre_taux obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}