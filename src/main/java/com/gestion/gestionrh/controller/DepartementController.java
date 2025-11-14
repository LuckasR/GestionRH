package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/departement")
public class DepartementController {

    @Autowired
    private DepartementService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("departements", service.getAll());
        return "departement/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("departement", new Departement());
        return "departement/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Departement obj) {
        service.save(obj);
        return "redirect:/departement";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("departement", service.getById(id));
        return "departement/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Departement obj) {
        service.save(obj);
        return "redirect:/departement";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/departement";
    }}
