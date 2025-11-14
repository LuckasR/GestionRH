package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "retard")
public class Retard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "pointage_id")
    private Pointage pointage;

    
    private Integer minutes_retard;

    
    private String justification;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private Status_general status_general;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    
    private String date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Pointage getPointage() { return pointage; }
    public void setPointage(Pointage pointage) { this.pointage = pointage; }

    public Integer getMinutes_retard() { return minutes_retard; }
    public void setMinutes_retard(Integer minutes_retard) { this.minutes_retard = minutes_retard; }

    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public String getDate_creation() { return date_creation; }
    public void setDate_creation(String date_creation) { this.date_creation = date_creation; }

}