package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/status_contrat")
public class Status_contratController {

    @Autowired
    private Status_contratService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("status_contrats", service.getAll());
        return "status_contrat/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("status_contrat", new Status_contrat());
        return "status_contrat/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Status_contrat obj) {
        service.save(obj);
        return "redirect:/status_contrat";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("status_contrat", service.getById(id));
        return "status_contrat/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Status_contrat obj) {
        service.save(obj);
        return "redirect:/status_contrat";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/status_contrat";
    }}
