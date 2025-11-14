package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getById(Integer id);
    void save(Genre obj);
    void delete(Integer id);
}