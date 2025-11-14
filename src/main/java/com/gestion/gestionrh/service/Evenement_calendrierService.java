package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Evenement_calendrierService {
    List<Evenement_calendrier> getAll();
    Evenement_calendrier getById(Integer id);
    void save(Evenement_calendrier obj);
    void delete(Integer id);
}