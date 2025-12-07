package com.gestion.gestionrh.model;
import jakarta.persistence.*;
import java.math.*;
import java.time.*;
import java.util.List;

@Entity
@Table(name = "horaire_travail")
public class Horaire_travail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String jour_semaine;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getJour_semaine() { return jour_semaine; }
    public void setJour_semaine(String jour_semaine) { this.jour_semaine = jour_semaine; }


    // public void getHeureTravail( LocalDateTime dateActu ){
    //     if( (dateActu.isAfter(this.date_debut)) && (date_fin == null) ){
    //             Duration d = Duration.between(start, end);
            

    //     }
    // }


    public Horaire_travail getHoraire( LocalDate date , List<Horaire_travail> values ){
        DateManager dm = new DateManager();
        String val = dm.getJourDeSemaine(date) ;
        for (Horaire_travail detail : values ) {
            if(detail.getJour_semaine().equalsIgnoreCase(val)){
                return detail ; 
            }
        } 
        return null ; 
    }
}