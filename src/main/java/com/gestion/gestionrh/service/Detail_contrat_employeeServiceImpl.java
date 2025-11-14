package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Detail_contrat_employeeServiceImpl implements Detail_contrat_employeeService {

    @Autowired
    private Detail_contrat_employeeRepository repo;

    public List<Detail_contrat_employee> getAll() { return repo.findAll(); }
    public Detail_contrat_employee getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Detail_contrat_employee obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}