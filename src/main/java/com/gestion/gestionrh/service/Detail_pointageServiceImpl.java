package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Detail_pointageServiceImpl implements Detail_pointageService {

    @Autowired
    private Detail_pointageRepository repo;

    public List<Detail_pointage> getAll() { return repo.findAll(); }
    public Detail_pointage getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Detail_pointage obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}