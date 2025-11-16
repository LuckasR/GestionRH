package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "absence")
public class Absence {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    
    private LocalDate date_absence;

    
    private Boolean est_justifie;

    
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private Status_general status_general;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    
    private LocalDateTime date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public LocalDate getDate_absence() { return date_absence; }
    public void setDate_absence(LocalDate date_absence) { this.date_absence = date_absence; }

    public Boolean getEst_justifie() { return est_justifie; }
    public void setEst_justifie(Boolean est_justifie) { this.est_justifie = est_justifie; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

}