package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/periode_paie")
public class Periode_paieController {

    @Autowired
    private Periode_paieService service;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("periode_paies", service.getAll());
        return "periode_paie/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("periode_paie", new Periode_paie());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "periode_paie/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Periode_paie obj) {
        service.save(obj);
        return "redirect:/periode_paie";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("periode_paie", service.getById(id));
        model.addAttribute("status_generalList", status_generalService.getAll());
        model.addAttribute("adminList", adminService.getAll());
        return "periode_paie/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Periode_paie obj) {
        service.save(obj);
        return "redirect:/periode_paie";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/periode_paie";
    }}
