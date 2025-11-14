package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Periode_paieService {
    List<Periode_paie> getAll();
    Periode_paie getById(Integer id);
    void save(Periode_paie obj);
    void delete(Integer id);
}