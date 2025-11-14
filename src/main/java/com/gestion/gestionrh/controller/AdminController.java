package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("admins", service.getAll());
        return "admin/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Admin obj) {
        service.save(obj);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("admin", service.getById(id));
        return "admin/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Admin obj) {
        service.save(obj);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/admin";
    }}
