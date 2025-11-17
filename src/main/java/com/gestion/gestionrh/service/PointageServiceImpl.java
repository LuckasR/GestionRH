package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointageServiceImpl implements PointageService {

    @Autowired
    private PointageRepository repo;

    public List<Pointage> getAll() {
        return repo.findAll();
    }

    public List<Pointage> getTodayPointage(Integer empId, LocalDate today) {
        return repo.findToday(empId, today);
    }
    public Pointage getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Pointage obj) {
        repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}