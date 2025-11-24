package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface Parametre_tauxService {
    List<Parametre_taux> getAll();
    Parametre_taux getById(Integer id);
    void save(Parametre_taux obj);
    Parametre_taux saveAndGet(Parametre_taux obj);
    void delete(Integer id);
      void validateFilterRanges(BigDecimal tauxEmpMin, BigDecimal tauxEmpMax,
                                     BigDecimal tauxEmployeurMin, BigDecimal tauxEmployeurMax,
                                     LocalDate dateDebut, LocalDate dateFin);
   List<Parametre_taux> filter(
    String code,
    BigDecimal tauxEmpMin,
    BigDecimal tauxEmpMax,
    BigDecimal tauxEmployeurMin,
    BigDecimal tauxEmployeurMax,
    LocalDate dateDebut,
    LocalDate dateFin,
    Boolean actif
    );    
}