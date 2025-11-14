package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "organisme_social")
public class Organisme_social {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String name;

    
    private BigDecimal pourcentage;

    
    private String date_debut;

    
    private String date_fin;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPourcentage() { return pourcentage; }
    public void setPourcentage(BigDecimal pourcentage) { this.pourcentage = pourcentage; }

    public String getDate_debut() { return date_debut; }
    public void setDate_debut(String date_debut) { this.date_debut = date_debut; }

    public String getDate_fin() { return date_fin; }
    public void setDate_fin(String date_fin) { this.date_fin = date_fin; }

}