package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Config_heure_supplementaireServiceImpl implements Config_heure_supplementaireService {

    @Autowired
    private Config_heure_supplementaireRepository repo;

    public List<Config_heure_supplementaire> getAll() { return repo.findAll(); }
    public Config_heure_supplementaire getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Config_heure_supplementaire obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}