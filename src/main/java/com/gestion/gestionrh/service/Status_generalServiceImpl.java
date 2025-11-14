package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Status_generalServiceImpl implements Status_generalService {

    @Autowired
    private Status_generalRepository repo;

    public List<Status_general> getAll() { return repo.findAll(); }
    public Status_general getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Status_general obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}