package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "regle_travail")
public class Regle_travail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String nom;

    
    private Integer duree_normale;

    
    private Boolean est_weekend;

    
    private Boolean est_ferie;

    
    private String description;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getDuree_normale() { return duree_normale; }
    public void setDuree_normale(Integer duree_normale) { this.duree_normale = duree_normale; }

    public Boolean getEst_weekend() { return est_weekend; }
    public void setEst_weekend(Boolean est_weekend) { this.est_weekend = est_weekend; }

    public Boolean getEst_ferie() { return est_ferie; }
    public void setEst_ferie(Boolean est_ferie) { this.est_ferie = est_ferie; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}