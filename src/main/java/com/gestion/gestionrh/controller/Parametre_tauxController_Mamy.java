package com.gestion.gestionrh.controller;
import java.util.List;
import java.time.*;
import java.math.*;
import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/parametre_taux_mamy")
public class Parametre_tauxController_Mamy {
    
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

    @PostMapping("/filter")
    public String filter(
                        @RequestParam(value="code",required = false) String code,
                        @RequestParam(value="date_debut",required = false) LocalDate date_debut,
                        @RequestParam(value="date_fin",required = false) LocalDate date_fin,
                        @RequestParam(value="tauxEmpMin",required = false) BigDecimal tauxEmpMin,
                        @RequestParam(value="tauxEmpMax",required = false) BigDecimal tauxEmpMax,
                        @RequestParam(value="tauxEmployeurMin",required = false) BigDecimal tauxEmployeurMin,
                        @RequestParam(value="tauxEmployeurMax",required = false) BigDecimal tauxEmployeurMax,
                        @RequestParam(value="actif",required = false) Boolean actif,
                        Model model
                        ){

        System.out.println("code "+code);
        System.out.println("date début "+date_debut);
        System.out.println("date fin "+date_fin);
        System.out.println("taux employe min "+tauxEmpMin);
        System.out.println("taux employe max "+tauxEmpMax);
        System.out.println("taux employeur min "+tauxEmployeurMin);
        System.out.println("taux employeur max "+tauxEmployeurMax);
        System.out.println("actif "+actif);

        List<Parametre_taux> list = service.filter(
            code,
            tauxEmpMin,
            tauxEmpMax,
            tauxEmployeurMin,
            tauxEmployeurMax,
            date_debut,
            date_fin,
            actif
        );

         System.out.println("📊 CONTROLLER - Résultats: " + list.size());


        model.addAttribute("parametre_tauxs",list);

        return "parametre_taux/listeAjax::parametre_tauxs";
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
