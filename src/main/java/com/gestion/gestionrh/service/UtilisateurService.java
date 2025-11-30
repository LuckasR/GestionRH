package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> getAll();
    Utilisateur getById(Integer id);
    void save(Utilisateur obj);
    void delete(Integer id);
    Utilisateur getByUsernameAndPassword(  String username , String password) ;
}