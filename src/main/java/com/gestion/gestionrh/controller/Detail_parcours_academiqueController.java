package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/detail_parcours_academique")
public class Detail_parcours_academiqueController {

    @Autowired
    private Detail_parcours_academiqueService service;

    @Autowired
    private Parcours_academiqueService parcours_academiqueService;

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private Niveau_etudeService niveau_etudeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("detail_parcours_academiques", service.getAll());
        return "detail_parcours_academique/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("detail_parcours_academique", new Detail_parcours_academique());
        model.addAttribute("parcours_academiques", parcours_academiqueService.getAll());
        model.addAttribute("filieres", filiereService.getAll());
        model.addAttribute("niveau_etudes", niveau_etudeService.getAll());
        return "detail_parcours_academique/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Detail_parcours_academique obj) {
        service.save(obj);
        return "redirect:/detail_parcours_academique";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_parcours_academique", service.getById(id));
        model.addAttribute("parcours_academiqueList", parcours_academiqueService.getAll());
        model.addAttribute("filiereList", filiereService.getAll());
        model.addAttribute("niveau_etudeList", niveau_etudeService.getAll());
        return "detail_parcours_academique/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Detail_parcours_academique obj) {
        service.save(obj);
        return "redirect:/detail_parcours_academique";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/detail_parcours_academique";
    }}
