package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/calendrier_entreprise")
public class Calendrier_entrepriseController {

    @Autowired
    private Calendrier_entrepriseService service;

    @Autowired
    private Evenement_calendrierService evenementCalendrierService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("calendrier_entreprises", service.getAll());
        return "calendrier_entreprise/index";
    }

    @GetMapping("/calendar/{annee}")
    public String calendarView(@PathVariable Integer annee, Model model) {
        model.addAttribute("annee", annee);
        Calendrier_entreprise calendrier = service.getAll().stream()
            .filter(c -> c.getAnnee() != null && c.getAnnee().equals(annee))
            .findFirst()
            .orElse(null);

        model.addAttribute("calendrier", calendrier);

        // Préparer les événements pour FullCalendar
        if (calendrier != null) {
            var evenements = evenementCalendrierService.getAll().stream()
                .filter(e -> e.getCalendrier_entreprise() != null &&
                           e.getCalendrier_entreprise().getId().equals(calendrier.getId()))
                .toList();
            model.addAttribute("evenements", evenements);
        } else {
            model.addAttribute("evenements", java.util.List.of());
        }

        return "calendrier_entreprise/calendar";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("calendrier_entreprise", new Calendrier_entreprise());
        return "calendrier_entreprise/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Calendrier_entreprise obj) {
        service.save(obj);
        return "redirect:/calendrier_entreprise";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("calendrier_entreprise", service.getById(id));
        return "calendrier_entreprise/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Calendrier_entreprise obj) {
        service.save(obj);
        return "redirect:/calendrier_entreprise";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/calendrier_entreprise";
    }}
