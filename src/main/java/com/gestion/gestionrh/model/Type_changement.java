package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "type_changement")
public class Type_changement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String name;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}