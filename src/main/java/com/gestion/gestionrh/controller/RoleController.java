package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("roles", service.getAll());
        return "role/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("role", new Role());
        return "role/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Role obj) {
        service.save(obj);
        return "redirect:/role";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("role", service.getById(id));
        return "role/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Role obj) {
        service.save(obj);
        return "redirect:/role";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/role";
    }}
