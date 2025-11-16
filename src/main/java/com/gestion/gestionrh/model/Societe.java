package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "societe")
public class Societe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String name;

    
    private Integer nombre_qcm_test;

    
    private BigDecimal durre_entretient;

    
    private Integer pourcentage_passed;

    
    private LocalDateTime date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getNombre_qcm_test() { return nombre_qcm_test; }
    public void setNombre_qcm_test(Integer nombre_qcm_test) { this.nombre_qcm_test = nombre_qcm_test; }

    public BigDecimal getDurre_entretient() { return durre_entretient; }
    public void setDurre_entretient(BigDecimal durre_entretient) { this.durre_entretient = durre_entretient; }

    public Integer getPourcentage_passed() { return pourcentage_passed; }
    public void setPourcentage_passed(Integer pourcentage_passed) { this.pourcentage_passed = pourcentage_passed; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

}