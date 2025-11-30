package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/detail_horaire")
public class Detail_horaireController {

    @Autowired
    private Detail_horaireService service;

    @Autowired
    private Horaire_travailService horaire_travailService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("detail_horaires", service.getAll());
        return "detail_horaire/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("detail_horaire", new Detail_horaire());
        model.addAttribute("horaire_travails", horaire_travailService.getAll());
        return "detail_horaire/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Detail_horaire obj) {
        service.save(obj);
        return "redirect:/detail_horaire";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_horaire", service.getById(id));
        model.addAttribute("horaire_travailList", horaire_travailService.getAll());
        return "detail_horaire/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Detail_horaire obj) {
        service.save(obj);
        return "redirect:/detail_horaire";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/detail_horaire";
    }}
