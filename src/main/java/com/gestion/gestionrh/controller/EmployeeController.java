package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartementService departementService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("employees", service.getAll());
        return "employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("departements", departementService.getAll());
        return "employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Employee obj) {
        service.save(obj);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", service.getById(id));
        model.addAttribute("roleList", roleService.getAll());
        model.addAttribute("departementList", departementService.getAll());
        return "employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Employee obj) {
        service.save(obj);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/employee";
    }}
