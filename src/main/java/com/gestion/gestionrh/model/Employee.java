package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "superviseur_id")
    private Employee superviseur;

    
    private String username;

    
    private String password;

    @Column(name = "code_qr")
    private String codeQr;

    public String getCodeQr() {
        return codeQr;
    }
    public void setCodeQr(String codeQr) {
        this.codeQr = codeQr;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }

    public Employee getSuperviseur() { return superviseur; }
    public void setSuperviseur(Employee superviseur) { this.superviseur = superviseur; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

   
}