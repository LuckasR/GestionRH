package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/niveau_etude")
public class Niveau_etudeController {

    @Autowired
    private Niveau_etudeService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("niveau_etudes", service.getAll());
        return "niveau_etude/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("niveau_etude", new Niveau_etude());
        return "niveau_etude/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Niveau_etude obj) {
        service.save(obj);
        return "redirect:/niveau_etude";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("niveau_etude", service.getById(id));
        return "niveau_etude/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Niveau_etude obj) {
        service.save(obj);
        return "redirect:/niveau_etude";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/niveau_etude";
    }}
