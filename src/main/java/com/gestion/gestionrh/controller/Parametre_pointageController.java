package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/parametre_pointage")
public class Parametre_pointageController {

    @Autowired
    private Parametre_pointageService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("parametre_pointages", service.getAll());
        return "parametre_pointage/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("parametre_pointage", new Parametre_pointage());
        return "parametre_pointage/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Parametre_pointage obj) {
        service.save(obj);
        return "redirect:/parametre_pointage";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("parametre_pointage", service.getById(id));
        return "parametre_pointage/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Parametre_pointage obj) {
        service.save(obj);
        return "redirect:/parametre_pointage";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/parametre_pointage";
    }}
