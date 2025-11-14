package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {}