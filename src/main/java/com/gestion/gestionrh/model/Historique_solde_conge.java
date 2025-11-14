package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "historique_solde_conge")
public class Historique_solde_conge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "solde_id")
    private Solde_conge solde_conge;

    @ManyToOne
    @JoinColumn(name = "type_operation_id")
    private Type_operation_conge type_operation_conge;

    
    private Integer jours_pris;

    
    private LocalDate date_debut;

    
    private LocalDate date_fin;

    
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    
    private String date_action;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Solde_conge getSolde_conge() { return solde_conge; }
    public void setSolde_conge(Solde_conge solde_conge) { this.solde_conge = solde_conge; }

    public Type_operation_conge getType_operation_conge() { return type_operation_conge; }
    public void setType_operation_conge(Type_operation_conge type_operation_conge) { this.type_operation_conge = type_operation_conge; }

    public Integer getJours_pris() { return jours_pris; }
    public void setJours_pris(Integer jours_pris) { this.jours_pris = jours_pris; }

    public LocalDate getDate_debut() { return date_debut; }
    public void setDate_debut(LocalDate date_debut) { this.date_debut = date_debut; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public String getDate_action() { return date_action; }
    public void setDate_action(String date_action) { this.date_action = date_action; }

}