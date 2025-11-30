package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;

import java.time.LocalDate;
import java.util.List;

public interface Detail_horaireService {
    List<Detail_horaire> getAll();
    Detail_horaire getById(Integer id);
    void save(Detail_horaire obj);
    void delete(Integer id);
    double getDayHourWork(LocalDate date , List<Horaire_travail> horaireList ) ; 

}