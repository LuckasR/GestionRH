package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Integer> {}