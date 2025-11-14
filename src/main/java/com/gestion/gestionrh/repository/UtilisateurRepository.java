package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {}