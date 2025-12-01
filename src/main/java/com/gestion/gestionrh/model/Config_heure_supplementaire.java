package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;
import java.lang.Integer;

@Entity
@Table(name = "config_heure_supplementaire")
public class Config_heure_supplementaire {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "regle_id")
    private Regle_travail regle_travail;

    
    private BigDecimal heure_max_jour;

    
    private BigDecimal heure_max_semaine;

    
    private BigDecimal taux_multiplicateur_jour;

    
    private BigDecimal taux_multiplicateur_nuit;

    
    private BigDecimal taux_multiplicateur_weekend;

    
    private BigDecimal taux_multiplicateur_ferie;

    @ManyToOne
    @JoinColumn(name = "type_compensation")
    private Type_compensation type_compensation;

    
    private Boolean besoin_validation_admin;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status_general status_general;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    
    private String commentaire;

    
    private LocalDateTime date_creation;

    
    private LocalDateTime date_fin;

    
    private Boolean est_actif;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Regle_travail getRegle_travail() { return regle_travail; }
    public void setRegle_travail(Regle_travail regle_travail) { this.regle_travail = regle_travail; }

    public BigDecimal getHeure_max_jour() { return heure_max_jour; }
    public void setHeure_max_jour(BigDecimal heure_max_jour) { this.heure_max_jour = heure_max_jour; }

    public BigDecimal getHeure_max_semaine() { return heure_max_semaine; }
    public void setHeure_max_semaine(BigDecimal heure_max_semaine) { this.heure_max_semaine = heure_max_semaine; }

    public BigDecimal getTaux_multiplicateur_jour() { return taux_multiplicateur_jour; }
    public void setTaux_multiplicateur_jour(BigDecimal taux_multiplicateur_jour) { this.taux_multiplicateur_jour = taux_multiplicateur_jour; }

    public BigDecimal getTaux_multiplicateur_nuit() { return taux_multiplicateur_nuit; }
    public void setTaux_multiplicateur_nuit(BigDecimal taux_multiplicateur_nuit) { this.taux_multiplicateur_nuit = taux_multiplicateur_nuit; }

    public BigDecimal getTaux_multiplicateur_weekend() { return taux_multiplicateur_weekend; }
    public void setTaux_multiplicateur_weekend(BigDecimal taux_multiplicateur_weekend) { this.taux_multiplicateur_weekend = taux_multiplicateur_weekend; }

    public BigDecimal getTaux_multiplicateur_ferie() { return taux_multiplicateur_ferie; }
    public void setTaux_multiplicateur_ferie(BigDecimal taux_multiplicateur_ferie) { this.taux_multiplicateur_ferie = taux_multiplicateur_ferie; }

    public Type_compensation getType_compensation() { return type_compensation; }
    public void setType_compensation(Type_compensation type_compensation) { this.type_compensation = type_compensation; }

    public Boolean getBesoin_validation_admin() { return besoin_validation_admin; }
    public void setBesoin_validation_admin(Boolean besoin_validation_admin) { this.besoin_validation_admin = besoin_validation_admin; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

    public LocalDateTime getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDateTime date_fin) { this.date_fin = date_fin; }

    public Boolean getEst_actif() { return est_actif; }
    public void setEst_actif(Boolean est_actif) { this.est_actif = est_actif; }

}