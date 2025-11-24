package com.gestion.gestionrh.controller;

import java.util.List;
import java.time.*;
import java.math.*;
import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parametre_taux_mamy")
public class Parametre_tauxController_Mamy {

    @Autowired
    private Parametre_tauxService service;

    @Autowired
    private TauxService tauxService;

    @Autowired
    private Parametre_detailService detailService;

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
    public String save(
            // A. Bind the Main Entity (Code, Desc, Date, Actif) automatically
            @ModelAttribute("parametre_taux") Parametre_taux parametre,
            // B. Catch the Rate fields manually
            @RequestParam("valeurTauxEmploye") Double valTauxEmp,
            @RequestParam("valeurTauxEmployeur") Double valTauxPatr,
            // C. Catch the Interval fields manually (Optional, so required=false)
            @RequestParam(name = "valeurMin", required = false) Double valMin,
            @RequestParam(name = "valeurMax", required = false) Double valMax
    ) {

        // --- STEP 1: Save the Parametre (Header) ---
        // Spring has already filled code, description, dates into the 'parametre' object
        Parametre_taux savedParam = service.saveAndGet(parametre);

        // --- STEP 2: Create and Save the Taux (Rate) ---
        Taux t = new Taux();
        t.setCode_taux("T_" + savedParam.getCode()); // Auto-generate code like 'T_CNAPS'
        t.setTaux_employe(BigDecimal.valueOf(valTauxEmp));
        t.setTaux_employeur(BigDecimal.valueOf(valTauxPatr));
        Taux savedTaux = tauxService.saveAndGet(t);

        // --- STEP 3: Create and Save the Detail (Link) ---
        Parametre_detail pd = new Parametre_detail();
        pd.setParametre_taux(savedParam);
        pd.setTaux(savedTaux);

        // Handle empty intervals (Global rule)
        if (valMin != null) {
            pd.setMontant_min(BigDecimal.valueOf(valMin));
        }
        if (valMax != null) {
            pd.setMontant_max(BigDecimal.valueOf(valMax));
        }

        detailService.save(pd);

        return "redirect:/parametre_taux_mamy";
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
        if (obj.getDetails() != null) {
            for (Parametre_detail detail : obj.getDetails()) {
                detail.setParametre_taux(obj); // <--- THIS LINE FIXES THE ERROR
            }
        }
        service.save(obj);
        return "redirect:/parametre_taux_mamy";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/parametre_taux_mamy";
    }
}
