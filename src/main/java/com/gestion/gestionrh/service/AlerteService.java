package com.gestion.gestionrh.service;

public interface AlerteService {
    void verifierCongesEnAttente();
    void verifierAbsencesRepetees();
    void envoyerAlertes();
}

