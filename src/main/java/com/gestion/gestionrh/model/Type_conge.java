package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "type_conge")
public class Type_conge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String libelle;

    
    private String description;

    
    private Integer jours_attribues_annuels;

    
    private Boolean is_payable;

    
    private Boolean is_cumulable;

    
    private LocalDateTime date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getJours_attribues_annuels() { return jours_attribues_annuels; }
    public void setJours_attribues_annuels(Integer jours_attribues_annuels) { this.jours_attribues_annuels = jours_attribues_annuels; }

    public Boolean getIs_payable() { return is_payable; }
    public void setIs_payable(Boolean is_payable) { this.is_payable = is_payable; }

    public Boolean getIs_cumulable() { return is_cumulable; }
    public void setIs_cumulable(Boolean is_cumulable) { this.is_cumulable = is_cumulable; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

}