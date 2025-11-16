package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "periode_paie")
public class Periode_paie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private Integer mois;

    
    private Integer annee;

    
    private LocalDate date_debut;

    
    private LocalDate date_fin;

    @ManyToOne
    @JoinColumn(name = "statut")
    private Status_general status_general;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    
    private LocalDateTime date_generation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getMois() { return mois; }
    public void setMois(Integer mois) { this.mois = mois; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public LocalDate getDate_debut() { return date_debut; }
    public void setDate_debut(LocalDate date_debut) { this.date_debut = date_debut; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public LocalDateTime getDate_generation() { return date_generation; }
    public void setDate_generation(LocalDateTime date_generation) { this.date_generation = date_generation; }

}