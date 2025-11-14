package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/historique_role")
public class Historique_roleController {

    @Autowired
    private Historique_roleService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("historique_roles", service.getAll());
        return "historique_role/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("historique_role", new Historique_role());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("roles", roleService.getAll());
        return "historique_role/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Historique_role obj) {
        service.save(obj);
        return "redirect:/historique_role";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("historique_role", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("roleList", roleService.getAll());
        return "historique_role/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Historique_role obj) {
        service.save(obj);
        return "redirect:/historique_role";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/historique_role";
    }}
