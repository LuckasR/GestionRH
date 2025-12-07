package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Detail_pointageService {
    List<Detail_pointage> getAll();
    Detail_pointage getById(Integer id);
    void save(Detail_pointage obj);
    void delete(Integer id);
}