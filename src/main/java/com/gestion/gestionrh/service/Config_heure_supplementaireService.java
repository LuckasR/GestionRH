package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Config_heure_supplementaireService {
    List<Config_heure_supplementaire> getAll();
    Config_heure_supplementaire getById(Integer id);
    void save(Config_heure_supplementaire obj);
    void delete(Integer id);
}