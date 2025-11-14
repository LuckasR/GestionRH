package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/absence")
public class AbsenceController {

    @Autowired
    private AbsenceService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("absences", service.getAll());
        return "absence/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("absence", new Absence());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "absence/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Absence obj) {
        service.save(obj);
        return "redirect:/absence";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("absence", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        model.addAttribute("adminList", adminService.getAll());
        return "absence/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Absence obj) {
        service.save(obj);
        return "redirect:/absence";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/absence";
    }}
