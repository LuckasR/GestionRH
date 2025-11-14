package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("genres", service.getAll());
        return "genre/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Genre obj) {
        service.save(obj);
        return "redirect:/genre";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("genre", service.getById(id));
        return "genre/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Genre obj) {
        service.save(obj);
        return "redirect:/genre";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/genre";
    }}
