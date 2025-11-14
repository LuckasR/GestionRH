package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/historique_solde_conge")
public class Historique_solde_congeController {

    @Autowired
    private Historique_solde_congeService service;

    @Autowired
    private Solde_congeService solde_congeService;

    @Autowired
    private Type_operation_congeService type_operation_congeService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("historique_solde_conges", service.getAll());
        return "historique_solde_conge/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("historique_solde_conge", new Historique_solde_conge());
        model.addAttribute("solde_conges", solde_congeService.getAll());
        model.addAttribute("type_operation_conges", type_operation_congeService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "historique_solde_conge/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Historique_solde_conge obj) {
        service.save(obj);
        return "redirect:/historique_solde_conge";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("historique_solde_conge", service.getById(id));
        model.addAttribute("solde_congeList", solde_congeService.getAll());
        model.addAttribute("type_operation_congeList", type_operation_congeService.getAll());
        model.addAttribute("adminList", adminService.getAll());
        return "historique_solde_conge/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Historique_solde_conge obj) {
        service.save(obj);
        return "redirect:/historique_solde_conge";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/historique_solde_conge";
    }}
