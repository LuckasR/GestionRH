package com.gestion.gestionrh.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.gestionrh.model.Contrat_employee;

public interface Contrat_employeeRepository extends JpaRepository<Contrat_employee, Integer> {

    // Contrats avec date_fin = date spécifique
    @Query("SELECT c FROM Contrat_employee c WHERE c.date_fin = :dateFin")
    List<Contrat_employee> findByDateFin(@Param("dateFin") LocalDate dateFin);
    
    // Contrats avec date_fin dépassée
    @Query("SELECT c FROM Contrat_employee c WHERE c.date_fin < CURRENT_DATE")
    List<Contrat_employee> findContratsTermines();                                                  
}