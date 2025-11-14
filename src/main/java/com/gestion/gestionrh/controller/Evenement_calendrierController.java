package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/evenement_calendrier")
public class Evenement_calendrierController {

    @Autowired
    private Evenement_calendrierService service;

    @Autowired
    private Calendrier_entrepriseService calendrier_entrepriseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Type_evenementService type_evenementService;

    @Autowired
    private Status_generalService status_generalService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("evenement_calendriers", service.getAll());
        return "evenement_calendrier/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("evenement_calendrier", new Evenement_calendrier());
        model.addAttribute("calendrier_entreprises", calendrier_entrepriseService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("type_evenements", type_evenementService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        return "evenement_calendrier/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Evenement_calendrier obj) {
        service.save(obj);
        return "redirect:/evenement_calendrier";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("evenement_calendrier", service.getById(id));
        model.addAttribute("calendrier_entrepriseList", calendrier_entrepriseService.getAll());
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("type_evenementList", type_evenementService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        return "evenement_calendrier/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Evenement_calendrier obj) {
        service.save(obj);
        return "redirect:/evenement_calendrier";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/evenement_calendrier";
    }}
