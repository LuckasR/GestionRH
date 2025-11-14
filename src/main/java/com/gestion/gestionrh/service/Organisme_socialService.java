package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Organisme_socialService {
    List<Organisme_social> getAll();
    Organisme_social getById(Integer id);
    void save(Organisme_social obj);
    void delete(Integer id);
}