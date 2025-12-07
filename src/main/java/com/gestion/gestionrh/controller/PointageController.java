package com.gestion.gestionrh.controller;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.service.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/pointage")
public class PointageController {

    @Autowired
    private PointageService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MethodeService methodeService;

    @Autowired
    private PointageService pointageService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pointages", service.getAll());
        return "pointage/index";
    }

    @GetMapping("/scan")
    public String scannerCode() {
        return "resultat_scan";
    }

    @PostMapping("/scan")
    public String scan(@RequestParam String qr, Model model) {

        // 1. Vérifier si le QR correspond à un employé
        Employee employee = employeeService.getByCodeQr(qr);

        if (employee == null) {
            model.addAttribute("error", "Code QR invalide ou employe introuvable");
            return "redirect:/pointage";
        }

        LocalDate today = LocalDate.now();

        // 2. Récupérer tous les pointages du jour
        List<Pointage> todayPointages = pointageService.getTodayPointage(employee.getId(), today);

        // 🔥 CAS 1 : Aucun pointage aujourd’hui → CHECK-IN
        if (todayPointages == null || todayPointages.isEmpty()) {

            Pointage p = new Pointage();
            p.setEmployee(employee);
            p.setDate_pointage(today);
            p.setHeure_arrivee(LocalTime.now());
            p.setDate_enregistrement(LocalDateTime.now());
            p.setCommentaire("Check-in via QR Code");

            Methode m = methodeService.getById(3);
            p.setMethode(m);

            pointageService.save(p);
            return "redirect:/pointage";
        }

        // Récupérer le dernier pointage (la liste est déjà ORDER BY id DESC)
        Pointage lastPointage = todayPointages.get(0);

        // 🔥 CAS 2 : Dernier pointage = check-in sans check-out
        if (lastPointage.getHeure_depart() == null) {

            lastPointage.setHeure_depart(LocalTime.now());
            lastPointage.setCommentaire("Check-out via QR Code");

            pointageService.save(lastPointage);
            return "redirect:/pointage";
        }

        // 🔥 CAS 3 : Il a déjà un IN + OUT → nouveau cycle (IN)
        Pointage newP = new Pointage();
        newP.setEmployee(employee);
        newP.setDate_pointage(today);
        newP.setHeure_arrivee(LocalTime.now());
        newP.setDate_enregistrement(LocalDateTime.now());
        newP.setCommentaire("Nouveau cycle de pointage via QR Code");

        Methode m = methodeService.getById(3);
        newP.setMethode(m);

        pointageService.save(newP);

        return "redirect:/pointage";
    }

    @GetMapping("/checkout/{id}")
    public String checkout(@PathVariable Integer id, Model model) {

        // 1. Chercher le pointage
        Pointage p = pointageService.getById(id);

        if (p == null) {
            model.addAttribute("error", "Pointage introuvable");
            return "redirect:/pointage";
        }

        // 2. Vérifier si l'heure_depart n'est pas déjà définie
        if (p.getHeure_depart() != null) {
            model.addAttribute("error", "Check-out deja effectue");
            return "redirect:/pointage";
        }

        // 3. Définir l’heure de départ
        p.setHeure_depart(LocalTime.now());

        // (optionnel) Ajouter un commentaire
        p.setCommentaire("Sortie par Check-out manuel");

        // 4. Sauvegarder
        pointageService.save(p);

        return "redirect:/pointage";
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
        obj.setDate_enregistrement(LocalDateTime.now());
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
    }}
