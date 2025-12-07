package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/detail_pointage")
public class Detail_pointageController {

    @Autowired
    private Detail_pointageService service;

    @Autowired
    private PointageService pointageService;

    @Autowired
    private MethodeService methodeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("detail_pointages", service.getAll());
        return "detail_pointage/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("detail_pointage", new Detail_pointage());
        model.addAttribute("pointages", pointageService.getAll());
        model.addAttribute("methodes", methodeService.getAll());
        return "detail_pointage/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Detail_pointage obj) {
        service.save(obj);
        return "redirect:/detail_pointage";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_pointage", service.getById(id));
        model.addAttribute("pointageList", pointageService.getAll());
        model.addAttribute("methodeList", methodeService.getAll());
        return "detail_pointage/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Detail_pointage obj) {
        service.save(obj);
        return "redirect:/detail_pointage";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/detail_pointage";
    }}
