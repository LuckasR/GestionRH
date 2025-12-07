package com.gestion.gestionrh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

 
    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate rest = new RestTemplate();

    public String ask(String question) {
        // URL corrigée avec v1beta + modèle qui existe vraiment
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey;

        Map<String, Object> body = Map.of(
            "contents", new Object[] {
                Map.of("role", "user",
                       "parts", new Object[] {
                           Map.of("text", question)
                       })
            },
            // Optionnel : sécurité + meilleure réponse
            "safetySettings", new Object[] {
                Map.of("category", "HARM_CATEGORY_HARASSMENT", "threshold", "BLOCK_NONE"),
                Map.of("category", "HARM_CATEGORY_HATE_SPEECH", "threshold", "BLOCK_NONE"),
                Map.of("category", "HARM_CATEGORY_SEXUALLY_EXPLICIT", "threshold", "BLOCK_NONE"),
                Map.of("category", "HARM_CATEGORY_DANGEROUS_CONTENT", "threshold", "BLOCK_NONE")
            },
            "generationConfig", Map.of(
                "temperature", 0.7,
                "maxOutputTokens", 1024
            )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, entity, Map.class);

            if (response.getBody() == null || !response.getBody().containsKey("candidates")) {
                return "Aucune réponse reçue de Gemini.";
            }

            var candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
            var content = (Map<String, Object>) candidates.get(0).get("content");
            var parts = (List<Map<String, String>>) content.get("parts");
            return parts.get(0).get("text");

        } catch (Exception e) {
            return "Erreur Gemini : " + e.getMessage();
        }
    }
}
 