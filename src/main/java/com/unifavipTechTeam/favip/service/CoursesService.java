package com.unifavipTechTeam.favip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unifavipTechTeam.favip.dto.CoursesDTO;
import com.unifavipTechTeam.favip.entity.Courses;
import com.unifavipTechTeam.favip.repositories.CoursesRepository;

@Service
public class CoursesService {
    private final CoursesRepository coursesRepository;

    public CoursesService (CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public List<CoursesDTO> getAllCourses() {
        return coursesRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CoursesDTO getCourseById(Long id) {
        return coursesRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public String postCourses(CoursesDTO coursesDTO) {
        Courses courses = new Courses();
        mapCourses(courses, coursesDTO);
        coursesRepository.save(courses);
        return "Courses " + coursesDTO.name() + " added successfully";
    }

    public String updateCourses(Long id, CoursesDTO coursesDTO) {
        Courses courses = coursesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Courses not found: " + id));
        mapCourses(courses, coursesDTO);
        coursesRepository.save(courses);
        return "Courses id: " + id + " updated successfully";
    }

    public Boolean deleteCourses(Long id) {
        Courses courses = coursesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Courses not found: " + id));
        coursesRepository.delete(courses);
        return true;
    }

    private void mapCourses(Courses courses, CoursesDTO coursesDTO) {
        courses.setName(coursesDTO.name());
    }

    private CoursesDTO convertToDTO(Courses courses) {
        return new CoursesDTO(
            courses.getId(),
            courses.getName());
        }
}
