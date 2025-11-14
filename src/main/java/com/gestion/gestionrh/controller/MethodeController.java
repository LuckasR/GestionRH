package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/methode")
public class MethodeController {

    @Autowired
    private MethodeService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("methodes", service.getAll());
        return "methode/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("methode", new Methode());
        return "methode/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Methode obj) {
        service.save(obj);
        return "redirect:/methode";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("methode", service.getById(id));
        return "methode/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Methode obj) {
        service.save(obj);
        return "redirect:/methode";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/methode";
    }}
