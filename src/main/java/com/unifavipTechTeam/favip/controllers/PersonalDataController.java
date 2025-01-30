package com.unifavipTechTeam.favip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unifavipTechTeam.favip.dto.PersonalDataDTO;
import com.unifavipTechTeam.favip.service.PersonalDataService;

@RestController
@RequestMapping("/personalData")
public class PersonalDataController {
    private final PersonalDataService personalDataService;

    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPersonalData() {
        try {
            return ResponseEntity.ok(personalDataService.getAllPersonalData());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPersonalDataById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personalDataService.getPersonalDataById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postPersonalData(@RequestBody PersonalDataDTO personalDataDTO) {
        try {
            return ResponseEntity.ok(personalDataService.postPersonalData(personalDataDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePersonalData(@PathVariable Long id, @RequestBody PersonalDataDTO personalDataDTO) {
        try {
            return ResponseEntity.ok(personalDataService.updatePersonalData(id, personalDataDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersonalData(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personalDataService.deletePersonalData(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
