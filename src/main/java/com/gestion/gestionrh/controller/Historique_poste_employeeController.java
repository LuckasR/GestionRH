package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/historique_poste_employee")
public class Historique_poste_employeeController {

    @Autowired
    private Historique_poste_employeeService service;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PosteService posteService;

    @Autowired
    private Type_changementService type_changementService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("historique_poste_employees", service.getAll());
        return "historique_poste_employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("historique_poste_employee", new Historique_poste_employee());
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("postes", posteService.getAll());
        model.addAttribute("type_changements", type_changementService.getAll());
        return "historique_poste_employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Historique_poste_employee obj) {
        service.save(obj);
        return "redirect:/historique_poste_employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("historique_poste_employee", service.getById(id));
        model.addAttribute("adminList", adminService.getAll());
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("posteList", posteService.getAll());
        model.addAttribute("type_changementList", type_changementService.getAll());
        return "historique_poste_employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Historique_poste_employee obj) {
        service.save(obj);
        return "redirect:/historique_poste_employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/historique_poste_employee";
    }}
