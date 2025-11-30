package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "salaire_employee")
public class Salaire_employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    
    private BigDecimal salaire;

    
    private String motif_modification;

    
    private LocalDate date_attribution;

    
    private LocalDate date_fin;

    
    private Boolean est_actif;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public BigDecimal getSalaire() { return salaire; }
    public void setSalaire(BigDecimal salaire) { this.salaire = salaire; }

    public String getMotif_modification() { return motif_modification; }
    public void setMotif_modification(String motif_modification) { this.motif_modification = motif_modification; }

    public LocalDate getDate_attribution() { return date_attribution; }
    public void setDate_attribution(LocalDate date_attribution) { this.date_attribution = date_attribution; }

    public LocalDate getDate_fin() { return date_fin; }
    public void setDate_fin(LocalDate date_fin) { this.date_fin = date_fin; }

    public Boolean getEst_actif() { return est_actif; }
    public void setEst_actif(Boolean est_actif) { this.est_actif = est_actif; }


    public BigDecimal getTauxHoraire() {
        return salaire.divide(new BigDecimal(173), RoundingMode.HALF_UP);
    }
}