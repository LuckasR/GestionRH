package com.gestion.gestionrh.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;


@Entity
@Table(name = "parametre_taux")
public class Parametre_taux {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    
    private String description;

    
    private LocalDate date_application;

    
    private LocalDate date_fin;

    
    private Boolean actif;
    @OneToMany(mappedBy = "parametre_taux", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parametre_detail> details = new ArrayList<>();

    // GETTER AND SETTER FOR THE LIST
    public List<Parametre_detail> getDetails() {
        return details;
    }

    public void setDetails(List<Parametre_detail> details) {
        this.details = details;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate_application() { return date_application; }
    public void setDate_application(LocalDate date_application) { this.date_application = date_application; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }

}