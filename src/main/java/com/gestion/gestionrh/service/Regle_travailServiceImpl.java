package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Regle_travailServiceImpl implements Regle_travailService {

    @Autowired
    private Regle_travailRepository repo;

    public List<Regle_travail> getAll() { return repo.findAll(); }
    public Regle_travail getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Regle_travail obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}