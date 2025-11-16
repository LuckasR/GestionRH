package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "assurance_social_employee")
public class Assurance_social_employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "organisme_id")
    private Organisme_social organisme_social;

    
    private LocalDateTime date_attribution;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Organisme_social getOrganisme_social() { return organisme_social; }
    public void setOrganisme_social(Organisme_social organisme_social) { this.organisme_social = organisme_social; }

    public LocalDateTime getDate_attribution() { return date_attribution; }
    public void setDate_attribution(LocalDateTime date_attribution) { this.date_attribution = date_attribution; }

}