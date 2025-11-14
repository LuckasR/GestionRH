package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Status_generalService {
    List<Status_general> getAll();
    Status_general getById(Integer id);
    void save(Status_general obj);
    void delete(Integer id);
}