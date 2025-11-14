package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Status_contratService {
    List<Status_contrat> getAll();
    Status_contrat getById(Integer id);
    void save(Status_contrat obj);
    void delete(Integer id);
}