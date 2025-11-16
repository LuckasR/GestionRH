package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "conge")
public class Conge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "type_conge_id")
    private Type_conge type_conge;

    
    private Integer nb_jours;

    
    private String commentaire;

    
    private LocalDateTime date_demande;

    
    private LocalDate date_debut;

    
    private LocalDate date_fin;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private Status_general status_general;

    
    private LocalDateTime date_validation;

    
    private String reference_demande;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Type_conge getType_conge() { return type_conge; }
    public void setType_conge(Type_conge type_conge) { this.type_conge = type_conge; }

    public Integer getNb_jours() { return nb_jours; }
    public void setNb_jours(Integer nb_jours) { this.nb_jours = nb_jours; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public LocalDateTime getDate_demande() { return date_demande; }
    public void setDate_demande(LocalDateTime date_demande) { this.date_demande = date_demande; }

    public LocalDate getDate_debut() { return date_debut; }
    public void setDate_debut(LocalDate date_debut) { this.date_debut = date_debut; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public LocalDateTime getDate_validation() { return date_validation; }
    public void setDate_validation(LocalDateTime date_validation) { this.date_validation = date_validation; }

    public String getReference_demande() { return reference_demande; }
    public void setReference_demande(String reference_demande) { this.reference_demande = reference_demande; }

}