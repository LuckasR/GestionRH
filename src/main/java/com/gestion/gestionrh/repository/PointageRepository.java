package com.gestion.gestionrh.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.gestionrh.model.Pointage;

public interface PointageRepository extends JpaRepository<Pointage, Integer> {


    @Query("SELECT p FROM Pointage p WHERE p.employee.id = :empId AND p.date_pointage = :today ORDER BY p.id DESC")
    List<Pointage> findToday(@Param("empId") Integer empId, @Param("today") LocalDate today);

    // List<Pointage> findToday(Integer empId, LocalDate today) ;                        
}
