package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/status_traitement")
public class Status_traitementController {

    @Autowired
    private Status_traitementService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("status_traitements", service.getAll());
        return "status_traitement/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("status_traitement", new Status_traitement());
        return "status_traitement/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Status_traitement obj) {
        service.save(obj);
        return "redirect:/status_traitement";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("status_traitement", service.getById(id));
        return "status_traitement/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Status_traitement obj) {
        service.save(obj);
        return "redirect:/status_traitement";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/status_traitement";
    }}
