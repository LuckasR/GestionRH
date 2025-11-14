package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/heure_supplementaire")
public class Heure_supplementaireController {

    @Autowired
    private Heure_supplementaireService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PointageService pointageService;

    @Autowired
    private Config_heure_supplementaireService config_heure_supplementaireService;

    @Autowired
    private Type_compensationService type_compensationService;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("heure_supplementaires", service.getAll());
        return "heure_supplementaire/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("heure_supplementaire", new Heure_supplementaire());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("pointages", pointageService.getAll());
        model.addAttribute("config_heure_supplementaires", config_heure_supplementaireService.getAll());
        model.addAttribute("type_compensations", type_compensationService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "heure_supplementaire/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Heure_supplementaire obj) {
        service.save(obj);
        return "redirect:/heure_supplementaire";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("heure_supplementaire", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("pointageList", pointageService.getAll());
        model.addAttribute("config_heure_supplementaireList", config_heure_supplementaireService.getAll());
        model.addAttribute("type_compensationList", type_compensationService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        model.addAttribute("adminList", adminService.getAll());
        return "heure_supplementaire/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Heure_supplementaire obj) {
        service.save(obj);
        return "redirect:/heure_supplementaire";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/heure_supplementaire";
    }}
