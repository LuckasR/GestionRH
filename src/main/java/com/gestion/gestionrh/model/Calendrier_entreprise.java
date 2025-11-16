package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "calendrier_entreprise")
public class Calendrier_entreprise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String titre;

    
    private String description;

    
    private Integer annee;

    
    private LocalDateTime date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

}