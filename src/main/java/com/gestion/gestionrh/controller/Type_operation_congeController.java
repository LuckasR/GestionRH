package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_operation_conge")
public class Type_operation_congeController {

    @Autowired
    private Type_operation_congeService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_operation_conges", service.getAll());
        return "type_operation_conge/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_operation_conge", new Type_operation_conge());
        return "type_operation_conge/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_operation_conge obj) {
        service.save(obj);
        return "redirect:/type_operation_conge";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_operation_conge", service.getById(id));
        return "type_operation_conge/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_operation_conge obj) {
        service.save(obj);
        return "redirect:/type_operation_conge";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_operation_conge";
    }}
