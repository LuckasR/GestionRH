package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Status_traitementServiceImpl implements Status_traitementService {

    @Autowired
    private Status_traitementRepository repo;

    public List<Status_traitement> getAll() { return repo.findAll(); }
    public Status_traitement getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Status_traitement obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}