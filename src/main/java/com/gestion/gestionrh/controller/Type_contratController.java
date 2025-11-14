package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_contrat")
public class Type_contratController {

    @Autowired
    private Type_contratService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_contrats", service.getAll());
        return "type_contrat/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_contrat", new Type_contrat());
        return "type_contrat/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_contrat obj) {
        service.save(obj);
        return "redirect:/type_contrat";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_contrat", service.getById(id));
        return "type_contrat/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_contrat obj) {
        service.save(obj);
        return "redirect:/type_contrat";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_contrat";
    }}
