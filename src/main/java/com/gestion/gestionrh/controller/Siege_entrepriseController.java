package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/siege_entreprise")
public class Siege_entrepriseController {

    @Autowired
    private Siege_entrepriseService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("siege_entreprises", service.getAll());
        return "siege_entreprise/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("siege_entreprise", new Siege_entreprise());
        return "siege_entreprise/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Siege_entreprise obj) {
        service.save(obj);
        return "redirect:/siege_entreprise";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("siege_entreprise", service.getById(id));
        return "siege_entreprise/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Siege_entreprise obj) {
        service.save(obj);
        return "redirect:/siege_entreprise";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/siege_entreprise";
    }}
