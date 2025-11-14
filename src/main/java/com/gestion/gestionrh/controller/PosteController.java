package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/poste")
public class PosteController {

    @Autowired
    private PosteService service;

    @Autowired
    private DepartementService departementService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("postes", service.getAll());
        return "poste/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("poste", new Poste());
        model.addAttribute("departements", departementService.getAll());
        return "poste/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Poste obj) {
        service.save(obj);
        return "redirect:/poste";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("poste", service.getById(id));
        model.addAttribute("departementList", departementService.getAll());
        return "poste/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Poste obj) {
        service.save(obj);
        return "redirect:/poste";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/poste";
    }}
