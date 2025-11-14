package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Type_contratServiceImpl implements Type_contratService {

    @Autowired
    private Type_contratRepository repo;

    public List<Type_contrat> getAll() { return repo.findAll(); }
    public Type_contrat getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Type_contrat obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}