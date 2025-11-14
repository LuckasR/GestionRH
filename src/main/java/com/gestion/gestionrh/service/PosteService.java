package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface PosteService {
    List<Poste> getAll();
    Poste getById(Integer id);
    void save(Poste obj);
    void delete(Integer id);
}