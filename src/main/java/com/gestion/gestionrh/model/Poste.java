package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "poste")
public class Poste {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    
    private String name;

    
    private BigDecimal salaire_base;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getSalaire_base() { return salaire_base; }
    public void setSalaire_base(BigDecimal salaire_base) { this.salaire_base = salaire_base; }

}