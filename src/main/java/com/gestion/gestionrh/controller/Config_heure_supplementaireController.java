package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/config_heure_supplementaire")
public class Config_heure_supplementaireController {

    @Autowired
    private Config_heure_supplementaireService service;

    @Autowired
    private Regle_travailService regle_travailService;

    @Autowired
    private Type_compensationService type_compensationService;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("config_heure_supplementaires", service.getAll());
        return "config_heure_supplementaire/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("config_heure_supplementaire", new Config_heure_supplementaire());
        model.addAttribute("regle_travails", regle_travailService.getAll());
        model.addAttribute("type_compensations", type_compensationService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "config_heure_supplementaire/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Config_heure_supplementaire obj) {
        service.save(obj);
        return "redirect:/config_heure_supplementaire";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("config_heure_supplementaire", service.getById(id));
        model.addAttribute("regle_travailList", regle_travailService.getAll());
        model.addAttribute("type_compensationList", type_compensationService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        model.addAttribute("adminList", adminService.getAll());
        return "config_heure_supplementaire/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Config_heure_supplementaire obj) {
        service.save(obj);
        return "redirect:/config_heure_supplementaire";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/config_heure_supplementaire";
    }}
