package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/organisme_social")
public class Organisme_socialController {

    @Autowired
    private Organisme_socialService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("organisme_socials", service.getAll());
        return "organisme_social/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("organisme_social", new Organisme_social());
        return "organisme_social/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Organisme_social obj) {
        service.save(obj);
        return "redirect:/organisme_social";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("organisme_social", service.getById(id));
        return "organisme_social/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Organisme_social obj) {
        service.save(obj);
        return "redirect:/organisme_social";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/organisme_social";
    }}
