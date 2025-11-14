package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/status_general")
public class Status_generalController {

    @Autowired
    private Status_generalService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("status_generals", service.getAll());
        return "status_general/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("status_general", new Status_general());
        return "status_general/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Status_general obj) {
        service.save(obj);
        return "redirect:/status_general";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("status_general", service.getById(id));
        return "status_general/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Status_general obj) {
        service.save(obj);
        return "redirect:/status_general";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/status_general";
    }}
