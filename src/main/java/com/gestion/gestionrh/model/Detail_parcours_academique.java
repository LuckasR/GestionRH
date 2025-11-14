package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "detail_parcours_academique")
public class Detail_parcours_academique {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parcours_id")
    private Parcours_academique parcours_academique;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "niveau_etude_id")
    private Niveau_etude niveau_etude;

    
    private String etablissement;

    
    private String mention;

    
    private String document_diplome;

    
    private LocalDate date_obtention;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Parcours_academique getParcours_academique() { return parcours_academique; }
    public void setParcours_academique(Parcours_academique parcours_academique) { this.parcours_academique = parcours_academique; }

    public Filiere getFiliere() { return filiere; }
    public void setFiliere(Filiere filiere) { this.filiere = filiere; }

    public Niveau_etude getNiveau_etude() { return niveau_etude; }
    public void setNiveau_etude(Niveau_etude niveau_etude) { this.niveau_etude = niveau_etude; }

    public String getEtablissement() { return etablissement; }
    public void setEtablissement(String etablissement) { this.etablissement = etablissement; }

    public String getMention() { return mention; }
    public void setMention(String mention) { this.mention = mention; }

    public String getDocument_diplome() { return document_diplome; }
    public void setDocument_diplome(String document_diplome) { this.document_diplome = document_diplome; }

    public LocalDate getDate_obtention() { return date_obtention; }
    public void setDate_obtention(LocalDate date_obtention) { this.date_obtention = date_obtention; }

}