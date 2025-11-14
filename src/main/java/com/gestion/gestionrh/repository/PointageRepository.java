package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Pointage;

public interface PointageRepository extends JpaRepository<Pointage, Integer> {}