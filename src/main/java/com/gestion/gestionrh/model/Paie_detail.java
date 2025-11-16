package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "paie_detail")
public class Paie_detail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "paie_id")
    private Paie_employee paie_employee;

    
    private String type;

    
    private String description;

    
    private BigDecimal montant;

    
    private LocalDateTime date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Paie_employee getPaie_employee() { return paie_employee; }
    public void setPaie_employee(Paie_employee paie_employee) { this.paie_employee = paie_employee; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

}