package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_evenement")
public class Type_evenementController {

    @Autowired
    private Type_evenementService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_evenements", service.getAll());
        return "type_evenement/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_evenement", new Type_evenement());
        return "type_evenement/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_evenement obj) {
        service.save(obj);
        return "redirect:/type_evenement";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_evenement", service.getById(id));
        return "type_evenement/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_evenement obj) {
        service.save(obj);
        return "redirect:/type_evenement";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_evenement";
    }}
