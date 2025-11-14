package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "detail_contrat_employee")
public class Detail_contrat_employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "contrat_employee_id")
    private Contrat_employee contrat_employee;

    @ManyToOne
    @JoinColumn(name = "type_contrat_id")
    private Type_contrat type_contrat;

    
    private Integer duree_contrat;

    
    private LocalDate date_debut_contrat;

    
    private LocalDate date_fin_contrat;

    
    private Integer periode_essai;

    
    private Integer duree_preavis;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private Status_contrat status_contrat;

    
    private Boolean renouvellement_auto;

    
    private String commentaire;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public Contrat_employee getContrat_employee() { return contrat_employee; }
    public void setContrat_employee(Contrat_employee contrat_employee) { this.contrat_employee = contrat_employee; }

    public Type_contrat getType_contrat() { return type_contrat; }
    public void setType_contrat(Type_contrat type_contrat) { this.type_contrat = type_contrat; }

    public Integer getDuree_contrat() { return duree_contrat; }
    public void setDuree_contrat(Integer duree_contrat) { this.duree_contrat = duree_contrat; }

    public LocalDate getDate_debut_contrat() { return date_debut_contrat; }
    public void setDate_debut_contrat(LocalDate date_debut_contrat) { this.date_debut_contrat = date_debut_contrat; }

    public LocalDate getDate_fin_contrat() { return date_fin_contrat; }
    public void setDate_fin_contrat(LocalDate date_fin_contrat) { this.date_fin_contrat = date_fin_contrat; }

    public Integer getPeriode_essai() { return periode_essai; }
    public void setPeriode_essai(Integer periode_essai) { this.periode_essai = periode_essai; }

    public Integer getDuree_preavis() { return duree_preavis; }
    public void setDuree_preavis(Integer duree_preavis) { this.duree_preavis = duree_preavis; }

    public Status_contrat getStatus_contrat() { return status_contrat; }
    public void setStatus_contrat(Status_contrat status_contrat) { this.status_contrat = status_contrat; }

    public Boolean getRenouvellement_auto() { return renouvellement_auto; }
    public void setRenouvellement_auto(Boolean renouvellement_auto) { this.renouvellement_auto = renouvellement_auto; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

}