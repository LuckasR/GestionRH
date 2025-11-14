package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Information_employeeServiceImpl implements Information_employeeService {

    @Autowired
    private Information_employeeRepository repo;

    public List<Information_employee> getAll() { return repo.findAll(); }
    public Information_employee getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Information_employee obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}