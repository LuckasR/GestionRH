 package com.gestion.gestionrh.controller;

import  com.gestion.gestionrh.service.GeminiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        return geminiService.ask(q);
    }
}
