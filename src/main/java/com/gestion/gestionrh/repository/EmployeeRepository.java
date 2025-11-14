package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}