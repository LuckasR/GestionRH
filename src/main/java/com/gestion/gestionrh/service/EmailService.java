package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;

public interface EmailService {
    void envoyerEmail(String destinataire, String sujet, String contenu);
    void envoyerNotificationConge(Conge conge);
    void envoyerNotificationValidationSuperviseur(Conge conge);
    void envoyerNotificationValidationRH(Conge conge);
    void envoyerAlerteCongeEnAttente(Conge conge);
    void envoyerAlerteAbsenceRepetee(Employee employee, int nombreAbsences);
}

