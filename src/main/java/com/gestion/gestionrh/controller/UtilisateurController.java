package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("utilisateurs", service.getAll());
        return "utilisateur/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateur/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Utilisateur obj) {
        service.save(obj);
        return "redirect:/utilisateur";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("utilisateur", service.getById(id));
        return "utilisateur/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Utilisateur obj) {
        service.save(obj);
        return "redirect:/utilisateur";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/utilisateur";
    }}
