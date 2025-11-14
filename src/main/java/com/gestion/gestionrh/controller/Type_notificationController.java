package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/type_notification")
public class Type_notificationController {

    @Autowired
    private Type_notificationService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("type_notifications", service.getAll());
        return "type_notification/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("type_notification", new Type_notification());
        return "type_notification/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Type_notification obj) {
        service.save(obj);
        return "redirect:/type_notification";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("type_notification", service.getById(id));
        return "type_notification/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type_notification obj) {
        service.save(obj);
        return "redirect:/type_notification";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/type_notification";
    }}
