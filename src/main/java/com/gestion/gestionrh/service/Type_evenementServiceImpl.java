package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Type_evenementServiceImpl implements Type_evenementService {

    @Autowired
    private Type_evenementRepository repo;

    public List<Type_evenement> getAll() { return repo.findAll(); }
    public Type_evenement getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Type_evenement obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}