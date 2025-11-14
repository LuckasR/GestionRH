package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/pointage")
public class PointageController {

    @Autowired
    private PointageService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MethodeService methodeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pointages", service.getAll());
        return "pointage/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pointage", new Pointage());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("methodes", methodeService.getAll());
        return "pointage/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Pointage obj) {
        service.save(obj);
        return "redirect:/pointage";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pointage", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("methodeList", methodeService.getAll());
        return "pointage/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Pointage obj) {
        service.save(obj);
        return "redirect:/pointage";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/pointage";
    }}
