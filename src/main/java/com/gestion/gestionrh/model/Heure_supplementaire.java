package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "heure_supplementaire")
public class Heure_supplementaire {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "pointage_id")
    private Pointage pointage;

    @ManyToOne
    @JoinColumn(name = "config_id")
    private Config_heure_supplementaire config_heure_supplementaire;

    
    private BigDecimal nb_heures;

    @ManyToOne
    @JoinColumn(name = "type_compensation")
    private Type_compensation type_compensation;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private Status_general status_general;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    
    private String commentaire;

    
    private String date_validation;

    
    private String date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Pointage getPointage() { return pointage; }
    public void setPointage(Pointage pointage) { this.pointage = pointage; }

    public Config_heure_supplementaire getConfig_heure_supplementaire() { return config_heure_supplementaire; }
    public void setConfig_heure_supplementaire(Config_heure_supplementaire config_heure_supplementaire) { this.config_heure_supplementaire = config_heure_supplementaire; }

    public BigDecimal getNb_heures() { return nb_heures; }
    public void setNb_heures(BigDecimal nb_heures) { this.nb_heures = nb_heures; }

    public Type_compensation getType_compensation() { return type_compensation; }
    public void setType_compensation(Type_compensation type_compensation) { this.type_compensation = type_compensation; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public String getDate_validation() { return date_validation; }
    public void setDate_validation(String date_validation) { this.date_validation = date_validation; }

    public String getDate_creation() { return date_creation; }
    public void setDate_creation(String date_creation) { this.date_creation = date_creation; }

}