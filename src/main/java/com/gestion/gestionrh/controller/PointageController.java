package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/pointage")
public class PointageController {

    @Autowired
    private PointageService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MethodeService methodeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pointages", service.getAll());
        return "pointage/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pointage", new Pointage());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("methodes", methodeService.getAll());
        return "pointage/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Pointage obj) {
        obj.setDate_enregistrement(LocalDateTime.now()) ; 
        service.save(obj);
        return "redirect:/pointage";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pointage", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("methodeList", methodeService.getAll());
        return "pointage/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Pointage obj) {
        service.save(obj);
        return "redirect:/pointage";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/pointage";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(value="empName",required=false) String empName,
                            @RequestParam(value="date_debut",required=false) String date_debut,
                            @RequestParam(value="date_fin",required=false) String date_fin,
                            @RequestParam(value="methodeId",required=false) Integer methodeId,
                            @RequestParam(value="heure_arrivee",required=false) String  heure_arrivee,
                            @RequestParam(value="heure_depart",required=false) String  heure_depart,
                            Model model) {
            // Call the service method to filter pointages
            List<Pointage> filteredPointages = service.filterPointages(empName, 
                                                                    date_debut.isEmpty() ? null : LocalDate.parse(date_debut), 
                                                                    date_fin.isEmpty() ? null : LocalDate.parse(date_fin), 
                                                                    methodeId, 
                                                                    heure_arrivee.isEmpty() ? null : LocalTime.parse(heure_arrivee), 
                                                                    heure_depart.isEmpty() ? null : LocalTime.parse(heure_depart)); 
           model.addAttribute("pointages", filteredPointages);
           System.out.println("📊 CONTROLLER - Résultats filtrés: " + filteredPointages.size());
            return "heure_supplementaire/listeAjaxPointage::pointages";
        }          
}
