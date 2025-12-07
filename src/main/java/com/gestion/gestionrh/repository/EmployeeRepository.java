package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.gestionrh.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT nextval('employee_code_qr_seq')", nativeQuery = true)
    Long getNextQrCodeNumber();

    Employee findByCodeQr(String codeQr);
}