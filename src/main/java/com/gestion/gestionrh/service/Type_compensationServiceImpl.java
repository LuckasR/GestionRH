package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Type_compensationServiceImpl implements Type_compensationService {

    @Autowired
    private Type_compensationRepository repo;

    public List<Type_compensation> getAll() { return repo.findAll(); }
    public Type_compensation getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Type_compensation obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}