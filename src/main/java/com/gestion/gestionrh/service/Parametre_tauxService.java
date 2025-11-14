package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Parametre_tauxService {
    List<Parametre_taux> getAll();
    Parametre_taux getById(Integer id);
    void save(Parametre_taux obj);
    void delete(Integer id);
}