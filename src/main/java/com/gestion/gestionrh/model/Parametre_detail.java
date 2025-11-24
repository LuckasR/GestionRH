package com.gestion.gestionrh.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import java.math.BigDecimal;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametre_detail")
public class Parametre_detail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parametre")
    private Parametre_taux parametre_taux;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_taux")
    private Taux taux;

    private BigDecimal montant_min;
    private BigDecimal montant_max;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parametre_taux getParametre_taux() {
        return parametre_taux;
    }

    public void setParametre_taux(Parametre_taux parametre_taux) {
        this.parametre_taux = parametre_taux;
    }

    public Taux getTaux() {
        return taux;
    }

    public void setTaux(Taux taux) {
        this.taux = taux;
    }

    public BigDecimal getMontant_min() {
        return montant_min;
    }

    public void setMontant_min(BigDecimal montant_min) {
        this.montant_min = montant_min;
    }

    public BigDecimal getMontant_max() {
        return montant_max;
    }

    public void setMontant_max(BigDecimal montant_max) {
        this.montant_max = montant_max;
    }

    
}
