package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Detail_parcours_academiqueServiceImpl implements Detail_parcours_academiqueService {

    @Autowired
    private Detail_parcours_academiqueRepository repo;

    public List<Detail_parcours_academique> getAll() { return repo.findAll(); }
    public Detail_parcours_academique getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Detail_parcours_academique obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}