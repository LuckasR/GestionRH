package com.gestion.gestionrh.repository;

import com.gestion.gestionrh.model.DetailPointage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DetailPointageRepository extends JpaRepository<DetailPointage, Integer> {

    List<DetailPointage> findByPointageId(Integer pointageId);

    List<DetailPointage> findByPointageEmployeeIdAndDatePointage(Integer empId, LocalDate date);
}
