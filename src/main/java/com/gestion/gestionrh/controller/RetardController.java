package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/retard")
public class RetardController {

    @Autowired
    private RetardService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PointageService pointageService;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("retards", service.getAll());
        return "retard/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("retard", new Retard());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("pointages", pointageService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "retard/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Retard obj) {
        service.save(obj);
        return "redirect:/retard";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("retard", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("pointageList", pointageService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        model.addAttribute("adminList", adminService.getAll());
        return "retard/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Retard obj) {
        service.save(obj);
        return "redirect:/retard";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/retard";
    }}
