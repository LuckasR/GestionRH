package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/salaire_employee")
public class Salaire_employeeController {

    @Autowired
    private Salaire_employeeService service;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("salaire_employees", service.getAll());
        return "salaire_employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("salaire_employee", new Salaire_employee());
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        return "salaire_employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Salaire_employee obj) {
        service.save(obj);
        return "redirect:/salaire_employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("salaire_employee", service.getById(id));
        model.addAttribute("adminList", adminService.getAll());
        model.addAttribute("employeeList", employeeService.getAll());
        return "salaire_employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Salaire_employee obj) {
        service.save(obj);
        return "redirect:/salaire_employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/salaire_employee";
    }}
