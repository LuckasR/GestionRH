package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "parametre_pointage")
public class Parametre_pointage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private Boolean pause_non_payee;

    
    private Boolean retard_rattrapable;

    
    private BigDecimal seuil_retard;

    
    private Boolean travail_weekend;

    
    private Boolean validation_automatique;

    
    private String date_modification;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Boolean getPause_non_payee() { return pause_non_payee; }
    public void setPause_non_payee(Boolean pause_non_payee) { this.pause_non_payee = pause_non_payee; }

    public Boolean getRetard_rattrapable() { return retard_rattrapable; }
    public void setRetard_rattrapable(Boolean retard_rattrapable) { this.retard_rattrapable = retard_rattrapable; }

    public BigDecimal getSeuil_retard() { return seuil_retard; }
    public void setSeuil_retard(BigDecimal seuil_retard) { this.seuil_retard = seuil_retard; }

    public Boolean getTravail_weekend() { return travail_weekend; }
    public void setTravail_weekend(Boolean travail_weekend) { this.travail_weekend = travail_weekend; }

    public Boolean getValidation_automatique() { return validation_automatique; }
    public void setValidation_automatique(Boolean validation_automatique) { this.validation_automatique = validation_automatique; }

    public String getDate_modification() { return date_modification; }
    public void setDate_modification(String date_modification) { this.date_modification = date_modification; }

}