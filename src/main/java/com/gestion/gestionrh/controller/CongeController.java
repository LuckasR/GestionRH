package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/conge")
public class CongeController {

    @Autowired
    private CongeService service;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Type_congeService type_congeService;

    @Autowired
    private Status_generalService status_generalService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("conges", service.getAll());
        return "conge/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("conge", new Conge());
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("type_conges", type_congeService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        return "conge/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Conge obj) {
        service.save(obj);
        return "redirect:/conge";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("conge", service.getById(id));
        model.addAttribute("adminList", adminService.getAll());
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("type_congeList", type_congeService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        return "conge/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Conge obj) {
        service.save(obj);
        return "redirect:/conge";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/conge";
    }}
