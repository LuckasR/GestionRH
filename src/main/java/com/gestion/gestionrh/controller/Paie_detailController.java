package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/paie_detail")
public class Paie_detailController {

    @Autowired
    private Paie_detailService service;

    @Autowired
    private Paie_employeeService paie_employeeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("paie_details", service.getAll());
        return "paie_detail/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("paie_detail", new Paie_detail());
        model.addAttribute("paie_employees", paie_employeeService.getAll());
        return "paie_detail/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Paie_detail obj) {
        service.save(obj);
        return "redirect:/paie_detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("paie_detail", service.getById(id));
        model.addAttribute("paie_employeeList", paie_employeeService.getAll());
        return "paie_detail/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Paie_detail obj) {
        service.save(obj);
        return "redirect:/paie_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/paie_detail";
    }}
