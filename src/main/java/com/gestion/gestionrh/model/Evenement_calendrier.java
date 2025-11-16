package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "evenement_calendrier")
public class Evenement_calendrier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "calendrier_id")
    private Calendrier_entreprise calendrier_entreprise;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "type_evenement_id")
    private Type_evenement type_evenement;

    
    private String titre;

    
    private String description;

    
    private LocalDate date_debut;

    
    private LocalDate date_fin;

    @ManyToOne
    @JoinColumn(name = "statut")
    private Status_general status_general;

    
    private String couleur;

    
    private LocalDateTime date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Calendrier_entreprise getCalendrier_entreprise() { return calendrier_entreprise; }
    public void setCalendrier_entreprise(Calendrier_entreprise calendrier_entreprise) { this.calendrier_entreprise = calendrier_entreprise; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Type_evenement getType_evenement() { return type_evenement; }
    public void setType_evenement(Type_evenement type_evenement) { this.type_evenement = type_evenement; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate_debut() { return date_debut; }
    public void setDate_debut(LocalDate date_debut) { this.date_debut = date_debut; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

}