package com.unifavipTechTeam.favip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unifavipTechTeam.favip.dto.FormationDTO;
import com.unifavipTechTeam.favip.service.FormationService;

@RestController
@RequestMapping("/formations")
public class FormationController {
    private final FormationService formationService;

    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllFormations() {
        try {
            return ResponseEntity.ok(formationService.getAllFormation());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getFormationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(formationService.getFormationById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postFormation(@RequestBody FormationDTO formationDTO) {
        try {
            return ResponseEntity.ok(formationService.postFormation(formationDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO) {
        try {
            return ResponseEntity.ok(formationService.updateFormation(id, formationDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFormation(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(formationService.deleteFormation(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
