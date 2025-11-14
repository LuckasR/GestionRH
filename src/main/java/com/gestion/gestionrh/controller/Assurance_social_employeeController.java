package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/assurance_social_employee")
public class Assurance_social_employeeController {

    @Autowired
    private Assurance_social_employeeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Organisme_socialService organisme_socialService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("assurance_social_employees", service.getAll());
        return "assurance_social_employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("assurance_social_employee", new Assurance_social_employee());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("organisme_socials", organisme_socialService.getAll());
        return "assurance_social_employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Assurance_social_employee obj) {
        service.save(obj);
        return "redirect:/assurance_social_employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("assurance_social_employee", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("organisme_socialList", organisme_socialService.getAll());
        return "assurance_social_employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Assurance_social_employee obj) {
        service.save(obj);
        return "redirect:/assurance_social_employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/assurance_social_employee";
    }}
