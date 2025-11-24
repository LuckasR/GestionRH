package com.gestion.gestionrh.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "taux")
public class Taux {
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    private String code_taux;
    private BigDecimal taux_employe;      
    private BigDecimal taux_employeur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode_taux() {
        return code_taux;
    }

    public void setCode_taux(String code_taux) {
        this.code_taux = code_taux;
    }

    public BigDecimal getTaux_employe() {
        return taux_employe;
    }

    public void setTaux_employe(BigDecimal taux_employe) {
        this.taux_employe = taux_employe;
    }

    public BigDecimal getTaux_employeur() {
        return taux_employeur;
    }

    public void setTaux_employeur(BigDecimal taux_employeur) {
        this.taux_employeur = taux_employeur;
    }
}
