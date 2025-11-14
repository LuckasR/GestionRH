package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "parcours_academique")
public class Parcours_academique {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    
    private String titre_parcours;

    
    private String description;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public String getTitre_parcours() { return titre_parcours; }
    public void setTitre_parcours(String titre_parcours) { this.titre_parcours = titre_parcours; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}