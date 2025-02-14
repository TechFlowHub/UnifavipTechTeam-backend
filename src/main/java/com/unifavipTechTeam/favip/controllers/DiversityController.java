package com.unifavipTechTeam.favip.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unifavipTechTeam.favip.dto.DiversityDTO;
import com.unifavipTechTeam.favip.service.DiversityService;

@RestController
@RequestMapping("/diversity")
public class DiversityController {
    private final DiversityService diversityService;

    public DiversityController(DiversityService diversityService) {
        this.diversityService = diversityService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllDiversity() {
        try {
            List<DiversityDTO> diversityDTOs = diversityService.getAllDiversity();
            return ResponseEntity.ok(diversityDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/  get/{id}")
    public ResponseEntity<?> getDiversityById(@PathVariable Long id) {
        try {
            DiversityDTO diversityDTO = diversityService.getDiversityById(id);
            return ResponseEntity.ok(diversityDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postDiversity(@RequestBody DiversityDTO diversityDTO) {
        try {
            String response = diversityService.postDiversity(diversityDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDiversity(@PathVariable Long id, @RequestBody DiversityDTO diversityDTO) {
        try {
            String response = diversityService.updateDiversity(id, diversityDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDiversity(@PathVariable Long id) {
        try {
            Boolean response = diversityService.deleteDiversity(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}