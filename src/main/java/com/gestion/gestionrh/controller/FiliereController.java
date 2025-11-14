package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/filiere")
public class FiliereController {

    @Autowired
    private FiliereService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("filieres", service.getAll());
        return "filiere/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("filiere", new Filiere());
        return "filiere/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Filiere obj) {
        service.save(obj);
        return "redirect:/filiere";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("filiere", service.getById(id));
        return "filiere/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Filiere obj) {
        service.save(obj);
        return "redirect:/filiere";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/filiere";
    }}
