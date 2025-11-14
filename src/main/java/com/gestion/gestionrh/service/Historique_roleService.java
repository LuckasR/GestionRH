package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Historique_roleService {
    List<Historique_role> getAll();
    Historique_role getById(Integer id);
    void save(Historique_role obj);
    void delete(Integer id);
}