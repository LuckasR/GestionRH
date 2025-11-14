package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Poste;

public interface PosteRepository extends JpaRepository<Poste, Integer> {}