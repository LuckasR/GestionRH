package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface RetardService {
    List<Retard> getAll();
    Retard getById(Integer id);
    void save(Retard obj);
    void delete(Integer id);
}