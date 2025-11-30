package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.DetailPointage;
import com.gestion.gestionrh.model.Horaire_travail;
import com.gestion.gestionrh.repository.DetailPointageRepository;
import com.gestion.gestionrh.service.DetailPointageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DetailPointageServiceImpl implements DetailPointageService {

    private final DetailPointageRepository repo;

    @Autowired
    private Detail_horaireService  detailService ; 

    public DetailPointageServiceImpl(DetailPointageRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DetailPointage> getAll() {
        return repo.findAll();
    }

    @Override
    public DetailPointage getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(DetailPointage obj) {
        repo.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<DetailPointage> getByPointageId(Integer pointageId) {
        return repo.findByPointageId(pointageId);
    }

    @Override
    public List<DetailPointage> getTodayDetails(Integer empId, LocalDate date) {
        return repo.findByPointageEmployeeIdAndDatePointage(empId, date);
    }

    // Heure de travail total d'un employé pour une date donnée
    public double getSumHoraireEmployee(Integer empId, LocalDate date) {
        List<DetailPointage> values = getTodayDetails(empId, date);
        double result = 0.0;
        for (DetailPointage dp : values) {
            LocalTime start = dp.getHeureArrivee();
            LocalTime end = dp.getHeureDepart();
            // Calculer la différence entre les deux heures
            if (start != null && end != null) {
                Duration d = Duration.between(start, end);
                result += d.toMinutes();
            }
        }
        return result;
    }

 

    public double getHeureSup(   Integer empId, LocalDate date , List<Horaire_travail> horaireList ){
        double HeureTravailEmployee = getSumHoraireEmployee(  empId,   date) ; 
        double heureNormalTravail =  detailService.getDayHourWork( date ,   horaireList )  ; 
        double heureSup =  HeureTravailEmployee -  heureNormalTravail  ;
        return heureSup ; 
    }

    public double getHeureSup(LocalDate dateDebut, LocalDate dateFin, Integer empId, List<Horaire_travail> horaireList) {
        if (dateDebut.isAfter(dateFin)) {
            throw new RuntimeException("La date de début est supérieure à la date de fin !");
        }

        double total = 0;

        LocalDate d = dateDebut;

        while (!d.isAfter(dateFin)) {
            total += this.getHeureSup(empId, d, horaireList);
            d = d.plusDays(1);
            System.out.println(d.getDayOfMonth());
        }
        return total;
    }
}
