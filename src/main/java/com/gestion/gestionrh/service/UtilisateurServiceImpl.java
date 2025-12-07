package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository repo;

    public List<Utilisateur> getAll() {
        return repo.findAll();
    }

    public Utilisateur getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Utilisateur getByUsernameAndPassword(  String username , String password) {
        return repo.findByUsernameAndPassword(username  , password);
    }



    ;


    public void save(Utilisateur obj) {
        repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}