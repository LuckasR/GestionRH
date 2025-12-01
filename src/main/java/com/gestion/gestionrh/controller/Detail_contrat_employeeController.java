package com.gestion.gestionrh.controller;

import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.gestionrh.model.Detail_contrat_employee;
import com.gestion.gestionrh.service.AdminService;
import com.gestion.gestionrh.service.Contrat_employeeService;
import com.gestion.gestionrh.service.Detail_contrat_employeeService;
import com.gestion.gestionrh.service.Status_contratService;
import com.gestion.gestionrh.service.Type_contratService;


@Controller
@RequestMapping("/detail_contrat_employee")
public class Detail_contrat_employeeController {

    @Autowired
    private Detail_contrat_employeeService service;

    @Autowired
    private AdminService adminService;

    @Autowired
    private Contrat_employeeService contrat_employeeService;

    @Autowired
    private Type_contratService type_contratService;

    @Autowired
    private Status_contratService status_contratService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("detail_contrat_employees", service.getAll());
        return "detail_contrat_employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("detail_contrat_employee", new Detail_contrat_employee());
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("contrat_employees", contrat_employeeService.getAll());
        model.addAttribute("type_contrats", type_contratService.getAll());
        model.addAttribute("status_contrats", status_contratService.getAll());
        return "detail_contrat_employee/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Detail_contrat_employee obj) {
        service.save(obj);
        return "redirect:/detail_contrat_employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_contrat_employee", service.getById(id));
        model.addAttribute("adminList", adminService.getAll());
        model.addAttribute("contrat_employeeList", contrat_employeeService.getAll());
        model.addAttribute("type_contratList", type_contratService.getAll());
        model.addAttribute("status_contratList", status_contratService.getAll());
        return "detail_contrat_employee/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Detail_contrat_employee obj) {
        service.save(obj);
        return "redirect:/detail_contrat_employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/detail_contrat_employee";
    }

    
}
