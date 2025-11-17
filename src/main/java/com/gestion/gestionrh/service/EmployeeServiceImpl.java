package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Integer id) {
        return repo.findById(id).orElse(null);
    }
    
    public String generateEmployeeCode() {
        Long seq = repo.getNextQrCodeNumber(); // ex : 1, 2, 3...
        return String.format("EMP-%03d", seq); // EMP-001, EMP-002, EMP-003...
    }
    public void save(Employee obj) {
        repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public Employee getByCodeQr(String qr) {
        return repo.findByCodeQr(qr);
    }
}