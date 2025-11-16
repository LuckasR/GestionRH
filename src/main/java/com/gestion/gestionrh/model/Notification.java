package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type_notification type_notification;

    
    private String message;

    
    private Boolean est_lu;

    
    private LocalDateTime date_creation;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Type_notification getType_notification() { return type_notification; }
    public void setType_notification(Type_notification type_notification) { this.type_notification = type_notification; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Boolean getEst_lu() { return est_lu; }
    public void setEst_lu(Boolean est_lu) { this.est_lu = est_lu; }

    public LocalDateTime getDate_creation() { return date_creation; }
    public void setDate_creation(LocalDateTime date_creation) { this.date_creation = date_creation; }

}