package com.unifavipTechTeam.favip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unifavipTechTeam.favip.dto.CoursesDTO;
import com.unifavipTechTeam.favip.service.CoursesService;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCourses() {
        try {
            return ResponseEntity.ok(coursesService.getAllCourses());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(coursesService.getCourseById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postCourse(@RequestBody CoursesDTO courseDTO) {
        try {
            return ResponseEntity.ok(coursesService.postCourses(courseDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CoursesDTO courseDTO) {
        try {
            return ResponseEntity.ok(coursesService.updateCourses(id, courseDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(coursesService.deleteCourses(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
