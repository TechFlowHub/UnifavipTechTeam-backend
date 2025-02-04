package com.unifavipTechTeam.favip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unifavipTechTeam.favip.dto.ExperienceDTO;

import com.unifavipTechTeam.favip.service.ExperienceService;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllExperience() {
        try {
            return ResponseEntity.ok(experienceService.getAllExperience());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")    
    public ResponseEntity<?> getExperienceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(experienceService.getExperienceById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postExperience(@RequestBody ExperienceDTO experienceDTO) {
        try {
            return ResponseEntity.ok(experienceService.postExperience(experienceDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExperience(@PathVariable Long id, @RequestBody ExperienceDTO experienceDTO) {
        try {
            return ResponseEntity.ok(experienceService.updateExperience(id, experienceDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(experienceService.deleteExperience(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
