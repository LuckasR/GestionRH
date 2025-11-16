package com.gestion.gestionrh.controller;

import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.gestionrh.model.Parametre_taux;
import com.gestion.gestionrh.service.Parametre_tauxService;


@Controller
@RequestMapping("/parametre_taux")
public class Parametre_tauxController {

    @Autowired
    private Parametre_tauxService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("parametre_tauxs", service.getAll());
        return "parametre_taux/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("parametre_taux", new Parametre_taux());
        return "parametre_taux/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Parametre_taux obj) {
        service.save(obj);
        return "redirect:/parametre_taux";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("parametre_taux", service.getById(id));
        return "parametre_taux/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Parametre_taux obj) {
        service.save(obj);
        return "redirect:/parametre_taux";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/parametre_taux";
    }
}
