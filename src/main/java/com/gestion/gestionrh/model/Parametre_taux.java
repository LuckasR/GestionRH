package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "parametre_taux")
public class Parametre_taux {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String code;

    
    private String description;

    
    private BigDecimal taux_employe;

    
    private BigDecimal taux_employeur;

    
    private LocalDate date_application;

    
    private LocalDate date_fin;

    
    private Boolean actif;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getTaux_employe() { return taux_employe; }
    public void setTaux_employe(BigDecimal taux_employe) { this.taux_employe = taux_employe; }

    public BigDecimal getTaux_employeur() { return taux_employeur; }
    public void setTaux_employeur(BigDecimal taux_employeur) { this.taux_employeur = taux_employeur; }

    public LocalDate getDate_application() { return date_application; }
    public void setDate_application(LocalDate date_application) { this.date_application = date_application; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }

}