package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "solde_conge")
public class Solde_conge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "type_conge_id")
    private Type_conge type_conge;

    
    private Integer annee;

    
    private Integer jours_acquis;

    
    private Integer jours_pris;

    
    private Integer jours_restants;

    
    private String date_maj;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Type_conge getType_conge() { return type_conge; }
    public void setType_conge(Type_conge type_conge) { this.type_conge = type_conge; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public Integer getJours_acquis() { return jours_acquis; }
    public void setJours_acquis(Integer jours_acquis) { this.jours_acquis = jours_acquis; }

    public Integer getJours_pris() { return jours_pris; }
    public void setJours_pris(Integer jours_pris) { this.jours_pris = jours_pris; }

    public Integer getJours_restants() { return jours_restants; }
    public void setJours_restants(Integer jours_restants) { this.jours_restants = jours_restants; }

    public String getDate_maj() { return date_maj; }
    public void setDate_maj(String date_maj) { this.date_maj = date_maj; }

}