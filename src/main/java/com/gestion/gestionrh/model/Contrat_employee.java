package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "contrat_employee")
public class Contrat_employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    
    private LocalDate date_debut;

    
    private LocalDate date_fin;

    
    private Boolean renouvellement_auto;

    
    private Boolean has_active;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public LocalDate getDate_debut() { return date_debut; }
    public void setDate_debut(LocalDate date_debut) { this.date_debut = date_debut; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public Boolean getRenouvellement_auto() { return renouvellement_auto; }
    public void setRenouvellement_auto(Boolean renouvellement_auto) { this.renouvellement_auto = renouvellement_auto; }

    public Boolean getHas_active() { return has_active; }
    public void setHas_active(Boolean has_active) { this.has_active = has_active; }

}