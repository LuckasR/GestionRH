package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Evenement_calendrierServiceImpl implements Evenement_calendrierService {

    @Autowired
    private Evenement_calendrierRepository repo;

    public List<Evenement_calendrier> getAll() { return repo.findAll(); }
    public Evenement_calendrier getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Evenement_calendrier obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}