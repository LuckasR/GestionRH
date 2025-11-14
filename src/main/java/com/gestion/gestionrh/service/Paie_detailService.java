package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Paie_detailService {
    List<Paie_detail> getAll();
    Paie_detail getById(Integer id);
    void save(Paie_detail obj);
    void delete(Integer id);
}