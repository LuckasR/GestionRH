package com.gestion.gestionrh.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Detail_horaire;
import com.gestion.gestionrh.model.Horaire_travail;

public interface Detail_horaireRepository extends JpaRepository<Detail_horaire, Integer> {
        List<Detail_horaire> findByHoraireTravail(Horaire_travail horaire);

}