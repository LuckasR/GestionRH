package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "pointage")
public class Pointage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    
    private LocalDate date_pointage;

    
    private String heure_arrivee;

    
    private String heure_depart;

    @ManyToOne
    @JoinColumn(name = "methode_id")
    private Methode methode;

    
    private String commentaire;

    
    private String date_enregistrement;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public LocalDate getDate_pointage() { return date_pointage; }
    public void setDate_pointage(LocalDate date_pointage) { this.date_pointage = date_pointage; }

    public String getHeure_arrivee() { return heure_arrivee; }
    public void setHeure_arrivee(String heure_arrivee) { this.heure_arrivee = heure_arrivee; }

    public String getHeure_depart() { return heure_depart; }
    public void setHeure_depart(String heure_depart) { this.heure_depart = heure_depart; }

    public Methode getMethode() { return methode; }
    public void setMethode(Methode methode) { this.methode = methode; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public String getDate_enregistrement() { return date_enregistrement; }
    public void setDate_enregistrement(String date_enregistrement) { this.date_enregistrement = date_enregistrement; }

}