package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "paie_employee")
public class Paie_employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "periode_id")
    private Periode_paie periode_paie;

    
    private BigDecimal salaire_base;

    
    private BigDecimal total_primes;

    
    private BigDecimal total_heures_sup;

    
    private BigDecimal total_absences;

    
    private BigDecimal total_deductions;

    
    private BigDecimal total_contributions;

    
    private BigDecimal net_a_payer;

    
    private LocalDateTime date_calcul;

    @ManyToOne
    @JoinColumn(name = "statut")
    private Status_general status_general;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Periode_paie getPeriode_paie() { return periode_paie; }
    public void setPeriode_paie(Periode_paie periode_paie) { this.periode_paie = periode_paie; }

    public BigDecimal getSalaire_base() { return salaire_base; }
    public void setSalaire_base(BigDecimal salaire_base) { this.salaire_base = salaire_base; }

    public BigDecimal getTotal_primes() { return total_primes; }
    public void setTotal_primes(BigDecimal total_primes) { this.total_primes = total_primes; }

    public BigDecimal getTotal_heures_sup() { return total_heures_sup; }
    public void setTotal_heures_sup(BigDecimal total_heures_sup) { this.total_heures_sup = total_heures_sup; }

    public BigDecimal getTotal_absences() { return total_absences; }
    public void setTotal_absences(BigDecimal total_absences) { this.total_absences = total_absences; }

    public BigDecimal getTotal_deductions() { return total_deductions; }
    public void setTotal_deductions(BigDecimal total_deductions) { this.total_deductions = total_deductions; }

    public BigDecimal getTotal_contributions() { return total_contributions; }
    public void setTotal_contributions(BigDecimal total_contributions) { this.total_contributions = total_contributions; }

    public BigDecimal getNet_a_payer() { return net_a_payer; }
    public void setNet_a_payer(BigDecimal net_a_payer) { this.net_a_payer = net_a_payer; }

    public LocalDateTime getDate_calcul() { return date_calcul; }
    public void setDate_calcul(LocalDateTime date_calcul) { this.date_calcul = date_calcul; }

    public Status_general getStatus_general() { return status_general; }
    public void setStatus_general(Status_general status_general) { this.status_general = status_general; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

}