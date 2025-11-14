package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Historique_roleServiceImpl implements Historique_roleService {

    @Autowired
    private Historique_roleRepository repo;

    public List<Historique_role> getAll() { return repo.findAll(); }
    public Historique_role getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Historique_role obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}