package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/solde_conge")
public class Solde_congeController {

    @Autowired
    private Solde_congeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Type_congeService type_congeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("solde_conges", service.getAll());
        return "solde_conge/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("solde_conge", new Solde_conge());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("type_conges", type_congeService.getAll());
        return "solde_conge/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Solde_conge obj) {
        service.save(obj);
        return "redirect:/solde_conge";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("solde_conge", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("type_congeList", type_congeService.getAll());
        return "solde_conge/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Solde_conge obj) {
        service.save(obj);
        return "redirect:/solde_conge";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/solde_conge";
    }}
