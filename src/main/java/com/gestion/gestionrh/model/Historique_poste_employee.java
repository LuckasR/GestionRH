package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "historique_poste_employee")
public class Historique_poste_employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "poste_id")
    private Poste poste;

    @ManyToOne
    @JoinColumn(name = "type_changement_id")
    private Type_changement type_changement;

    
    private LocalDate date_debut;

    
    private LocalDate date_fin;

    
    private String commentaire;

    
    private String date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Poste getPoste() { return poste; }
    public void setPoste(Poste poste) { this.poste = poste; }

    public Type_changement getType_changement() { return type_changement; }
    public void setType_changement(Type_changement type_changement) { this.type_changement = type_changement; }

    public LocalDate getDate_debut() { return date_debut; }
    public void setDate_debut(LocalDate date_debut) { this.date_debut = date_debut; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public String getDate_creation() { return date_creation; }
    public void setDate_creation(String date_creation) { this.date_creation = date_creation; }

}