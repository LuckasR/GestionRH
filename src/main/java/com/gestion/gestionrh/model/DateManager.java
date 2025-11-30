package com.gestion.gestionrh.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateManager {
        /**
     * Retourne le nom du jour de la semaine en français pour une date donnée.
     *
     * @param annee L'année de la date.
     * @param mois  Le mois de la date (1 pour janvier, 12 pour décembre).
     * @param jour  Le jour du mois.
     * @return Le nom du jour de la semaine (ex: "lundi", "mardi", etc.).
     */
    public String getDayOfWeek(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return dayName;
    }

    public String getDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return dayName;
    }

    public String getDayOfWeek(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return dayName;
    }

    public String getJourDeSemaine(int annee, int mois, int jour) {
        LocalDate date = LocalDate.of(annee, mois, jour);
        DayOfWeek jourDeSemaine = date.getDayOfWeek(); 
        String nomDuJour = jourDeSemaine.getDisplayName(TextStyle.FULL, Locale.FRANCE);
        return nomDuJour;
    }

    public String getJourDeSemaine( LocalDate date ) {
        DayOfWeek jourDeSemaine = date.getDayOfWeek(); 
        String nomDuJour = jourDeSemaine.getDisplayName(TextStyle.FULL, Locale.FRANCE);
        return nomDuJour;
    }

    public String getJourDeSemaine( LocalDateTime date ) {
        DayOfWeek jourDeSemaine = date.getDayOfWeek(); 
        String nomDuJour = jourDeSemaine.getDisplayName(TextStyle.FULL, Locale.FRANCE);
        return nomDuJour;
    }
 

}
