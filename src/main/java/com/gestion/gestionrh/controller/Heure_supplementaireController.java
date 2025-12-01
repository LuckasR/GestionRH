package com.gestion.gestionrh.controller;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/heure_supplementaire")
public class Heure_supplementaireController {

    @Autowired
    private Heure_supplementaireService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PointageService pointageService;

    @Autowired
    private Config_heure_supplementaireService config_heure_supplementaireService;

    @Autowired
    private Type_compensationService type_compensationService;

    @Autowired
    private Status_generalService status_generalService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PointageService PointageService;

    @Autowired
    private MethodeService methodeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("heure_supplementaires", service.getAll());
        model.addAttribute("pointages", PointageService.getAll());
          model.addAttribute("type_compensations", type_compensationService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("methodes", methodeService.getAll());

        return "heure_supplementaire/index";
    }

    @GetMapping("/create")
    public String createForm(@RequestParam Integer pointage_id,
            @RequestParam Integer employee_id, Model model) {

        Heure_supplementaire hs = new Heure_supplementaire();

        // Set the data so the "Read Only" inputs are filled
        hs.setPointage(pointageService.getById(pointage_id));
        hs.setEmployee(employeeService.getById(employee_id));
        hs.setDate_creation(LocalDateTime.now());

        model.addAttribute("heure_supplementaire", hs);
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("pointages", pointageService.getAll());
        model.addAttribute("config_heure_supplementaires", config_heure_supplementaireService.getAll());
        model.addAttribute("type_compensations", type_compensationService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "heure_supplementaire/create";
    }
        


    @PostMapping("/save")
    public String save(@ModelAttribute Heure_supplementaire obj) {
        service.save(obj);
        return "redirect:/heure_supplementaire";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(value="empName",required=false) String empName,
                            @RequestParam(value="date_debut",required=false) String date_debut,
                            @RequestParam(value="date_fin",required=false) String date_fin,
                            @RequestParam(value="statusId",required=false) Integer statusId,
                            @RequestParam(value="adminId",required=false) Integer adminId,
                            @RequestParam(value="typeCompensationId",required=false) Integer typeCompensationId,
                            Model model) {

        System.out.println("empName "+empName);
        System.out.println("date_debut "+date_debut);
        System.out.println("date_fin "+date_fin);
        System.out.println("statusId "+statusId);
        System.out.println("adminId "+adminId);
        System.out.println("typeCompensationId "+typeCompensationId);

        LocalDateTime dateDebut = (date_debut != null && !date_debut.isEmpty()) ? LocalDateTime.parse(date_debut) : null;
        LocalDateTime dateFin = (date_fin != null && !date_fin.isEmpty()) ? LocalDateTime.parse(date_fin) : null;

        List<Heure_supplementaire> list = service.filterHeureSup(empName, dateDebut, dateFin, statusId, adminId, typeCompensationId);

         System.out.println("📊 CONTROLLER - Résultats: " + list.size());
        model.addAttribute("heure_supplementaires",list);
        return "heure_supplementaire/listeAjaxHeureSup::heure_supplementaires";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("heure_supplementaire", service.getById(id));
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("pointageList", pointageService.getAll());
        model.addAttribute("config_heure_supplementaires", config_heure_supplementaireService.getAll());
        model.addAttribute("type_compensations", type_compensationService.getAll());
        model.addAttribute("status_generals", status_generalService.getAll());
        model.addAttribute("admins", adminService.getAll());
        return "heure_supplementaire/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Heure_supplementaire obj) {
        obj.setDate_creation(LocalDateTime.now());
        service.save(obj);
        return "redirect:/heure_supplementaire";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/heure_supplementaire";
    }
}
