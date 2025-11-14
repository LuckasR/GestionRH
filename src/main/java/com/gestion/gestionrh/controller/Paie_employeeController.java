package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/paie_employee")
public class Paie_employeeController {

    @Autowired
    private Paie_employeeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Periode_paieService periode_paieService;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("paie_employees", service.getAll());
        return "paie_employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("paie_employee", new Paie_employee());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("periode_paies", periode_paieService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "paie_employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Paie_employee obj) {
        service.save(obj);
        return "redirect:/paie_employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("paie_employee", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("periode_paieList", periode_paieService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        model.addAttribute("adminList", adminService.getAll());
        return "paie_employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Paie_employee obj) {
        service.save(obj);
        return "redirect:/paie_employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/paie_employee";
    }}
