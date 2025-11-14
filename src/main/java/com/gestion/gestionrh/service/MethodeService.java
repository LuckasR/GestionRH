package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface MethodeService {
    List<Methode> getAll();
    Methode getById(Integer id);
    void save(Methode obj);
    void delete(Integer id);
}