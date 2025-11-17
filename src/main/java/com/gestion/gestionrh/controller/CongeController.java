package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Controller
@RequestMapping("/conge")
public class CongeController {

    @Autowired
    private CongeService service;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Type_congeService type_congeService;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("conges", service.getAll());
        return "conge/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("conge", new Conge());
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("type_conges", type_congeService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        return "conge/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Conge obj) {
        // Générer une référence unique pour la demande
        if (obj.getReference_demande() == null || obj.getReference_demande().isEmpty()) {
            obj.setReference_demande("CONG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        
        // Définir la date de demande
        if (obj.getDate_demande() == null) {
            obj.setDate_demande(LocalDateTime.now());
        }
        
        // Définir le superviseur à partir de l'employé
        if (obj.getEmployee() != null && obj.getEmployee().getSuperviseur() != null) {
            obj.setSuperviseur(obj.getEmployee().getSuperviseur());
        }
        
        // Définir le statut initial à "En attente"
        if (obj.getStatus_general() == null) {
            obj.setStatus_general(status_generalService.getAll().stream()
                .filter(s -> "En attente".equals(s.getName()) || "En attente de validation".equals(s.getName()))
                .findFirst()
                .orElse(status_generalService.getAll().isEmpty() ? null : status_generalService.getAll().get(0)));
        }
        
        service.save(obj);
        
        // Envoyer notification à l'employé et au superviseur
        emailService.envoyerNotificationConge(obj);
        if (obj.getSuperviseur() != null) {
            emailService.envoyerNotificationValidationSuperviseur(obj);
        }
        
        return "redirect:/conge";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("conge", service.getById(id));
        model.addAttribute("adminList", adminService.getAll());
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("type_congeList", type_congeService.getAll());
        model.addAttribute("status_generalList", status_generalService.getAll());
        return "conge/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Conge obj) {
        service.save(obj);
        return "redirect:/conge";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/conge";
    }

    @GetMapping("/valider-superviseur/{id}")
    public String validerParSuperviseurForm(@PathVariable Integer id, Model model) {
        Conge conge = service.getById(id);
        if (conge == null) {
            return "redirect:/conge";
        }
        model.addAttribute("conge", conge);
        model.addAttribute("status_generals", status_generalService.getAll());
        return "conge/valider-superviseur";
    }

    @PostMapping("/valider-superviseur/{id}")
    public String validerParSuperviseur(@PathVariable Integer id,
                                        @RequestParam Integer statutId) {
        service.validerParSuperviseur(id, statutId);
        return "redirect:/conge";
    }

    @GetMapping("/valider-rh/{id}")
    public String validerParRHForm(@PathVariable Integer id, Model model) {
        Conge conge = service.getById(id);
        if (conge == null) {
            return "redirect:/conge";
        }
        model.addAttribute("conge", conge);
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        return "conge/valider-rh";
    }

    @PostMapping("/valider-rh/{id}")
    public String validerParRH(@PathVariable Integer id,
                              @RequestParam Integer adminId,
                              @RequestParam Integer statutId) {
        service.validerParRH(id, adminId, statutId);
        return "redirect:/conge";
    }

    @GetMapping("/mes-demandes/{employeeId}")
    public String mesDemandes(@PathVariable Integer employeeId, Model model) {
        model.addAttribute("conges", service.getAll().stream()
            .filter(c -> c.getEmployee() != null && c.getEmployee().getId().equals(employeeId))
            .toList());
        return "conge/index";
    }

    @GetMapping("/a-valider-superviseur/{superviseurId}")
    public String aValiderParSuperviseur(@PathVariable Integer superviseurId, Model model) {
        model.addAttribute("conges", service.getAll().stream()
            .filter(c -> c.getSuperviseur() != null && c.getSuperviseur().getId().equals(superviseurId)
                && (c.getStatut_validation_superviseur() == null ||
                    "En attente".equals(c.getStatut_validation_superviseur().getName())))
            .toList());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("superviseurId", superviseurId);
        return "conge/validation-superviseur";
    }
}
