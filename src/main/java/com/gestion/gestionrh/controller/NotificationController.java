package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Type_notificationService type_notificationService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("notifications", service.getAll());
        return "notification/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("notification", new Notification());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("type_notifications", type_notificationService.getAll());
        return "notification/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Notification obj) {
        service.save(obj);
        return "redirect:/notification";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("notification", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("type_notificationList", type_notificationService.getAll());
        return "notification/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Notification obj) {
        service.save(obj);
        return "redirect:/notification";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/notification";
    }}
