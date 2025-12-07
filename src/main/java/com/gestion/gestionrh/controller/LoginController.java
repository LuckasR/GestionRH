package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.model.*;
import  com.gestion.gestionrh.service.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller 
public class LoginController {

    @Autowired
    private UtilisateurService service;

    @Autowired
    private Horaire_travailService horaireService  ;


    @Autowired
    private DetailPointageService detailService ; 
 
    @GetMapping("/")
    public String index( Model model) {
        return "login";
    }


    @PostMapping("/checkLogin")
    public String index( @RequestParam("username") String username  ,@RequestParam("password") String password  , Model model , HttpSession session) {
        // model.addAttribute("utilisateurs", service.getAll());
        List<Horaire_travail> values = horaireService.getAll()  ;
        session.setAttribute("horaireList", values)  ;
        if (username.equals("a") && password.equals("a")) {
            System.out.println("Lasaaa ah e ! ");
        return "redirect:/utilisateur/create";
        }
        return "login";
    }
    
    @GetMapping("/test/emp/{id}")
    public String index( Model model , @PathVariable Integer id  , HttpSession session) {
        double hsup =  detailService.getHeureSup(    21 ,  LocalDate.now() , (List<Horaire_travail>)session.getAttribute("horaireList")    ) ; 
        System.out.println(hsup);
        return "test";
    }


}
