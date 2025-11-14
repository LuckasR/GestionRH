package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/log_action")
public class Log_actionController {

    @Autowired
    private Log_actionService service;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("log_actions", service.getAll());
        return "log_action/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("log_action", new Log_action());
        model.addAttribute("admins", adminService.getAll());
        return "log_action/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Log_action obj) {
        service.save(obj);
        return "redirect:/log_action";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("log_action", service.getById(id));
        model.addAttribute("adminList", adminService.getAll());
        return "log_action/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Log_action obj) {
        service.save(obj);
        return "redirect:/log_action";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/log_action";
    }}
