package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Type_operation_congeServiceImpl implements Type_operation_congeService {

    @Autowired
    private Type_operation_congeRepository repo;

    public List<Type_operation_conge> getAll() { return repo.findAll(); }
    public Type_operation_conge getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Type_operation_conge obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}