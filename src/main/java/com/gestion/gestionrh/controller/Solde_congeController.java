package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/solde_conge")
public class Solde_congeController {

    @Autowired
    private Solde_congeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Type_congeService type_congeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("solde_conges", service.getAll());
        return "solde_conge/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("solde_conge", new Solde_conge());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("type_conges", type_congeService.getAll());
        return "solde_conge/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Solde_conge obj) {
        service.save(obj);
        return "redirect:/solde_conge";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("solde_conge", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("type_congeList", type_congeService.getAll());
        return "solde_conge/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Solde_conge obj) {
        service.save(obj);
        return "redirect:/solde_conge";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/solde_conge";
    }

    @GetMapping("/recalculer/{employeeId}")
    public String recalculerSoldes(@PathVariable Integer employeeId, 
                                    @RequestParam(required = false) Integer annee) {
        Employee employee = employeeService.getById(employeeId);
        if (employee != null) {
            Integer anneeCalcul = annee != null ? annee : java.time.Year.now().getValue();
            service.calculerSoldeConge(employee, anneeCalcul);
        }
        return "redirect:/solde_conge";
    }

    @GetMapping("/recalculer-tous")
    public String recalculerTousSoldes(@RequestParam(required = false) Integer annee) {
        Integer anneeCalcul = annee != null ? annee : java.time.Year.now().getValue();
        List<Employee> employees = employeeService.getAll();
        for (Employee employee : employees) {
            service.calculerSoldeConge(employee, anneeCalcul);
        }
        return "redirect:/solde_conge";
    }

    @GetMapping("/par-employe/{employeeId}")
    public String soldesParEmploye(@PathVariable Integer employeeId, Model model) {
        Employee employee = employeeService.getById(employeeId);
        if (employee != null) {
            model.addAttribute("employee", employee);
            model.addAttribute("solde_conges", service.getSoldesByEmployee(employee));
            model.addAttribute("type_conges", type_congeService.getAll());
        }
        return "solde_conge/par-employe";
    }
}
