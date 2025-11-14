package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "log_action")
public class Log_action {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    
    private String action_type;

    
    private String description;

    
    private String date_action;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public String getAction_type() { return action_type; }
    public void setAction_type(String action_type) { this.action_type = action_type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDate_action() { return date_action; }
    public void setDate_action(String date_action) { this.date_action = date_action; }

}