package com.gestion.gestionrh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.gestionrh.service.AbsenceService;
import com.gestion.gestionrh.service.EmployeeService;

@Controller
@RequestMapping("/")
public class DashboardController {

        @Autowired
    private AbsenceService service;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model) {
        return "dashboard/index";
    }

    
}
