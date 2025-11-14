package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/regle_travail")
public class Regle_travailController {

    @Autowired
    private Regle_travailService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("regle_travails", service.getAll());
        return "regle_travail/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("regle_travail", new Regle_travail());
        return "regle_travail/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Regle_travail obj) {
        service.save(obj);
        return "redirect:/regle_travail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("regle_travail", service.getById(id));
        return "regle_travail/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Regle_travail obj) {
        service.save(obj);
        return "redirect:/regle_travail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/regle_travail";
    }}
