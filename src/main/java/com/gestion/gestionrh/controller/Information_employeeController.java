package com.gestion.gestionrh.controller;

import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.gestionrh.model.Information_employee;
import com.gestion.gestionrh.service.Contrat_employeeService;
import com.gestion.gestionrh.service.EmployeeService;
import com.gestion.gestionrh.service.Information_employeeService;


@Controller
@RequestMapping("/information_employee")
public class Information_employeeController {

    @Autowired
    private Information_employeeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Contrat_employeeService contrat_employeeService;
    
    @GetMapping("/moreInformation/{idemployee}")
    public String moreInformationForm(Model model, @PathVariable Integer idemployee) {
        model.addAttribute("information_employees", service.findAllByEmployeeId(idemployee));
        return "information_employee/moreInformation";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("information_employees", service.getAll());
        return "information_employee/index";
    }



    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("information_employee", new Information_employee());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("contrat_employees", contrat_employeeService.getAll());
        return "information_employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Information_employee obj) {
        service.save(obj);
        return "redirect:/information_employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("information_employee", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("contrat_employeeList", contrat_employeeService.getAll());
        return "information_employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Information_employee obj) {
        service.save(obj);
        return "redirect:/information_employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/information_employee";
    }}
