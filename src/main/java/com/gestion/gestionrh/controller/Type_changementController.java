package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_changement")
public class Type_changementController {

    @Autowired
    private Type_changementService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_changements", service.getAll());
        return "type_changement/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_changement", new Type_changement());
        return "type_changement/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_changement obj) {
        service.save(obj);
        return "redirect:/type_changement";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_changement", service.getById(id));
        return "type_changement/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_changement obj) {
        service.save(obj);
        return "redirect:/type_changement";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_changement";
    }}
