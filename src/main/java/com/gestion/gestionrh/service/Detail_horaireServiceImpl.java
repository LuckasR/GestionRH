package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Detail_horaireServiceImpl implements Detail_horaireService {

    @Autowired
    private Detail_horaireRepository repo;

    public List<Detail_horaire> getAll() {
        return repo.findAll();
    }

    public Detail_horaire getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Detail_horaire obj) {
        repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    /**
     * Retourne la somme des heure de travail dune date 
     *
     * @param date date travail employee .
     * @param List  list stocker en session .
     * @return heure de travail dun jour specifique .
     */
    public double getDayHourWork(LocalDate date , List<Horaire_travail> horaireList ) {
        Horaire_travail hr = new Horaire_travail() ; 
        Horaire_travail horaireCorrespond  = hr.getHoraire( date  ,  horaireList  ) ; 
        List<Detail_horaire> values = repo.findByHoraireTravail( horaireCorrespond );
        double val = 0.0 ; 
        for (Detail_horaire detail : values) {
            val+= detail.getHourWork() ; 
        }
        return val ; 
    }
}