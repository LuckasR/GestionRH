package com.gestion.gestionrh.model;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "detail_pointage")
public class DetailPointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relation vers pointage
    @ManyToOne
    @JoinColumn(name = "pointage_id")
    private Pointage pointage;

    @Column(name = "date_pointage", nullable = false)
    private LocalDate datePointage;

    @Column(name = "heure_arrivee")
    private LocalTime heureArrivee;

    @Column(name = "heure_depart")
    private LocalTime heureDepart;

    @ManyToOne
    @JoinColumn(name = "methode_id")
    private Methode methode;

    private String commentaire;

    // Getters & Setters

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public Pointage getPointage() { 
        return pointage; 
    }
    public void setPointage(Pointage pointage) { 
        this.pointage = pointage; 
    }

    public LocalDate getDatePointage() { 
        return datePointage; 
    }
    public void setDatePointage(LocalDate datePointage) { 
        this.datePointage = datePointage; 
    }

    public LocalTime getHeureArrivee() { 
        return heureArrivee; 
    }
    public void setHeureArrivee(LocalTime heureArrivee) { 
        this.heureArrivee = heureArrivee; 
    }

    public LocalTime getHeureDepart() { 
        return heureDepart; 
    }
    public void setHeureDepart(LocalTime heureDepart) { 
        this.heureDepart = heureDepart; 
    }

    public Methode getMethode() { 
        return methode; 
    }
    public void setMethode(Methode methode) { 
        this.methode = methode; 
    }

    public String getCommentaire() { 
        return commentaire; 
    }
    public void setCommentaire(String commentaire) { 
        this.commentaire = commentaire; 
    }
}
