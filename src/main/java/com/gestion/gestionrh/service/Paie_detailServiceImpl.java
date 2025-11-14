package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Paie_detailServiceImpl implements Paie_detailService {

    @Autowired
    private Paie_detailRepository repo;

    public List<Paie_detail> getAll() { return repo.findAll(); }
    public Paie_detail getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Paie_detail obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}