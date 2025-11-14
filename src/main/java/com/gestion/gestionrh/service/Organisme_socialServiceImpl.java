package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Organisme_socialServiceImpl implements Organisme_socialService {

    @Autowired
    private Organisme_socialRepository repo;

    public List<Organisme_social> getAll() { return repo.findAll(); }
    public Organisme_social getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Organisme_social obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}