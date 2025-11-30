package com.gestion.gestionrh.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.gestionrh.model.Detail_horaire;
import com.gestion.gestionrh.model.Horaire_travail;

public interface Horaire_travailRepository extends JpaRepository<Horaire_travail, Integer> {
}