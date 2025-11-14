package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Status_contratServiceImpl implements Status_contratService {

    @Autowired
    private Status_contratRepository repo;

    public List<Status_contrat> getAll() { return repo.findAll(); }
    public Status_contrat getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Status_contrat obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}