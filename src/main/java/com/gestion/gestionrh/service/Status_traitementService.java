package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Status_traitementService {
    List<Status_traitement> getAll();
    Status_traitement getById(Integer id);
    void save(Status_traitement obj);
    void delete(Integer id);
}