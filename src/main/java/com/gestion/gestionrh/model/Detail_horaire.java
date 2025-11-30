package com.gestion.gestionrh.model;

import jakarta.persistence.*;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "detail_horaire")
public class Detail_horaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_horaire")
    private Horaire_travail horaireTravail;

        private LocalDateTime heure_debut;
        private LocalDateTime heure_fin;
        private LocalDateTime date_fin;
        private LocalDateTime date_debut;


    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Horaire_travail getHoraireTravail() {
        return horaireTravail;
    }

    public void setHoraireTravail(Horaire_travail horaireTravail) {
        this.horaireTravail = horaireTravail;
    }

    public LocalDateTime getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(LocalDateTime heure_debut) {
        this.heure_debut = heure_debut;
    }

    public LocalDateTime getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(LocalDateTime heure_fin) {
        this.heure_fin = heure_fin;
    }

    public double getHourWork(){
        return Duration.between(this.heure_debut , this.heure_fin).toMinutes()  ; 
    }

}