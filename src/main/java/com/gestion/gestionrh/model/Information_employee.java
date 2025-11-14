package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "information_employee")
public class Information_employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat_employee contrat_employee;

    
    private BigDecimal salaire;

    
    private String first_name;

    
    private String last_name;

    
    private String email;

    
    private String phone;

    
    private String address;

    
    private String cv;

    
    private String lm;

    
    private String cin;

    
    private String residence;

    
    private String date_creation;

    
    private LocalDate date_naissance;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Contrat_employee getContrat_employee() { return contrat_employee; }
    public void setContrat_employee(Contrat_employee contrat_employee) { this.contrat_employee = contrat_employee; }

    public BigDecimal getSalaire() { return salaire; }
    public void setSalaire(BigDecimal salaire) { this.salaire = salaire; }

    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCv() { return cv; }
    public void setCv(String cv) { this.cv = cv; }

    public String getLm() { return lm; }
    public void setLm(String lm) { this.lm = lm; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getResidence() { return residence; }
    public void setResidence(String residence) { this.residence = residence; }

    public String getDate_creation() { return date_creation; }
    public void setDate_creation(String date_creation) { this.date_creation = date_creation; }

    public LocalDate getDate_naissance() { return date_naissance; }
    public void setDate_naissance(LocalDate date_naissance) { this.date_naissance = date_naissance; }

}