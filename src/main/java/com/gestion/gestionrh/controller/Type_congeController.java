package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_conge")
public class Type_congeController {

    @Autowired
    private Type_congeService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_conges", service.getAll());
        return "type_conge/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_conge", new Type_conge());
        return "type_conge/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_conge obj) {
        service.save(obj);
        return "redirect:/type_conge";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_conge", service.getById(id));
        return "type_conge/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_conge obj) {
        service.save(obj);
        return "redirect:/type_conge";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_conge";
    }}
