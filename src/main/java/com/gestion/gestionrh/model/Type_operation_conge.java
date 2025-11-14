package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "type_operation_conge")
public class Type_operation_conge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String nom;

    
    private String description;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}