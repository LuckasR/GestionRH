package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_compensation")
public class Type_compensationController {

    @Autowired
    private Type_compensationService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_compensations", service.getAll());
        return "type_compensation/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_compensation", new Type_compensation());
        return "type_compensation/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_compensation obj) {
        service.save(obj);
        return "redirect:/type_compensation";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_compensation", service.getById(id));
        return "type_compensation/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_compensation obj) {
        service.save(obj);
        return "redirect:/type_compensation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_compensation";
    }}
