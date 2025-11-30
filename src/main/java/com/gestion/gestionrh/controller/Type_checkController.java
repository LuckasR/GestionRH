package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_check")
public class Type_checkController {

    @Autowired
    private Type_checkService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_checks", service.getAll());
        return "type_check/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_check", new Type_check());
        return "type_check/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_check obj) {
        service.save(obj);
        return "redirect:/type_check";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_check", service.getById(id));
        return "type_check/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_check obj) {
        service.save(obj);
        return "redirect:/type_check";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_check";
    }}
