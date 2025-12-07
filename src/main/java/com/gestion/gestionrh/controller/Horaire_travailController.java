package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/horaire_travail")
public class Horaire_travailController {

    @Autowired
    private Horaire_travailService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("horaire_travails", service.getAll());
        return "horaire_travail/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("horaire_travail", new Horaire_travail());
        return "horaire_travail/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Horaire_travail obj) {
        service.save(obj);
        return "redirect:/horaire_travail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("horaire_travail", service.getById(id));
        return "horaire_travail/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Horaire_travail obj) {
        service.save(obj);
        return "redirect:/horaire_travail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/horaire_travail";
    }}
