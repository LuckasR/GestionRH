package com.gestion.gestionrh.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.gestionrh.model.Information_employee;

public interface Information_employeeRepository extends JpaRepository<Information_employee, Integer> {
   List<Information_employee> findAllByEmployeeId(Integer employeeId);
}