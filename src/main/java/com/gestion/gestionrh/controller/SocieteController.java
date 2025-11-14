package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/societe")
public class SocieteController {

    @Autowired
    private SocieteService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("societes", service.getAll());
        return "societe/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("societe", new Societe());
        return "societe/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Societe obj) {
        service.save(obj);
        return "redirect:/societe";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("societe", service.getById(id));
        return "societe/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Societe obj) {
        service.save(obj);
        return "redirect:/societe";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/societe";
    }}
