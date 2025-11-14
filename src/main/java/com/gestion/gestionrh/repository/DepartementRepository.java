package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {}