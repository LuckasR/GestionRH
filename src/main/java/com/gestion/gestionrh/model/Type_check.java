package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "type_check")
public class Type_check {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String name;

 
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return name; }
    public void setNom(String name) { this.name = name; }
 
}