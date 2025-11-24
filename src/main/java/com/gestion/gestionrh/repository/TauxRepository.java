package com.gestion.gestionrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Taux;

public interface TauxRepository extends JpaRepository<Taux, Integer> {
    
}
