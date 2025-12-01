package com.gestion.gestionrh.controller;

import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.gestionrh.model.Contrat_employee;
import com.gestion.gestionrh.service.Contrat_employeeService;
import com.gestion.gestionrh.service.EmployeeService;


@Controller
@RequestMapping("/contrat_employee")
public class Contrat_employeeController {

    @Autowired
    private Contrat_employeeService service;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model) {
        // Liste normale des contrats
        model.addAttribute("contrat_employees", service.getAll());
        
        // DONNÉES POUR LES ALERTES
        model.addAttribute("contratsAujourdhui", service.getContratsFinissantAujourdhui());
        model.addAttribute("contratsTermines", service.getContratsTermines());
        
        return "contrat_employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("contrat_employee", new Contrat_employee());
        model.addAttribute("employees", employeeService.getAll());
        return "contrat_employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Contrat_employee obj) {
        service.save(obj);
        return "redirect:/contrat_employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("contrat_employee", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        return "contrat_employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Contrat_employee obj) {
        service.save(obj);
        return "redirect:/contrat_employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/contrat_employee";
    }}
