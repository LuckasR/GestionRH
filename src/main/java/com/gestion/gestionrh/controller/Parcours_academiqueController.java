package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/parcours_academique")
public class Parcours_academiqueController {

    @Autowired
    private Parcours_academiqueService service;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("parcours_academiques", service.getAll());
        return "parcours_academique/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("parcours_academique", new Parcours_academique());
        model.addAttribute("employees", employeeService.getAll());
        return "parcours_academique/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Parcours_academique obj) {
        service.save(obj);
        return "redirect:/parcours_academique";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("parcours_academique", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        return "parcours_academique/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Parcours_academique obj) {
        service.save(obj);
        return "redirect:/parcours_academique";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/parcours_academique";
    }}
