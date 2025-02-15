package com.unifavipTechTeam.favip.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

import com.unifavipTechTeam.favip.dto.PersonalDataDTO;
import com.unifavipTechTeam.favip.entity.Courses;
import com.unifavipTechTeam.favip.entity.Diversity;
import com.unifavipTechTeam.favip.entity.Experience;
import com.unifavipTechTeam.favip.entity.Formation;
import com.unifavipTechTeam.favip.entity.PersonalData;
import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.repositories.CoursesRepository;
import com.unifavipTechTeam.favip.repositories.DiversityRepository;
import com.unifavipTechTeam.favip.repositories.PersonalDataRepository;
import com.unifavipTechTeam.favip.repositories.UserRepository;

@Service
public class PersonalDataService {
    private final PersonalDataRepository personalDataRepository;
    private final UserRepository userRepository;
    private final DiversityRepository diversityRepository;
    private final CoursesRepository coursesRepository;

    public PersonalDataService (PersonalDataRepository personalDataRepository, UserRepository userRepository, DiversityRepository diversityRepository,
        CoursesRepository coursesRepository) {
        this.personalDataRepository = personalDataRepository;
        this.userRepository = userRepository;
        this.diversityRepository = diversityRepository;
        this.coursesRepository = coursesRepository;
    }

    public List<PersonalDataDTO> getAllPersonalData() {
        return personalDataRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PersonalDataDTO getPersonalDataById(Long id) {
        return personalDataRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public String postPersonalData(PersonalDataDTO personalDataDTO) {
        PersonalData personalData = new PersonalData();
        mapPersonalData(personalData, personalDataDTO);
        personalDataRepository.save(personalData);
        return "Personal data for user " + personalDataDTO.user() + " added successfully";
    }

    public String updatePersonalData(Long id, PersonalDataDTO personalDataDTO) {
        PersonalData personalData = personalDataRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));
        mapPersonalData(personalData, personalDataDTO);
        personalDataRepository.save(personalData);
        return "Personal data id: " + id + "updated sucessfully";
    }

    public Boolean deletePersonalData(Long id) {
        PersonalData personalData = personalDataRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));
        personalDataRepository.delete(personalData);
        return true;
    }
    
    private void mapPersonalData(PersonalData personalData, PersonalDataDTO personalDataDTO) {
        personalData.setName(personalDataDTO.name());
        personalData.setContactEmail(personalDataDTO.contactEmail());
        personalData.setGithubLink(personalDataDTO.githubLink());
        personalData.setLinkedinLink(personalDataDTO.linkedinLink());
        personalData.setDisability(personalDataDTO.disability());
        personalData.setDisabilityDescription(personalDataDTO.disabilityDescription());
        personalData.setBirthDate(personalDataDTO.birthDate());
    
        User user = userRepository.findById(personalDataDTO.user())
                .orElseThrow(() -> new RuntimeException("User not found: " + personalDataDTO.user()));
        personalData.setUser(user);
    
        if (personalDataDTO.diversity() != null) {
            Diversity diversity = diversityRepository.findById(personalDataDTO.diversity())
                    .orElseThrow(() -> new RuntimeException("User diversity not found: " + personalDataDTO.diversity()));
            personalData.setDiversity(diversity);
        } else {
            personalData.setDiversity(null);
        }
    
        Courses courses = coursesRepository.findById(personalDataDTO.course())
                .orElseThrow(() -> new RuntimeException("Course not found: " + personalDataDTO.course()));
        personalData.setCourses(courses);
    
        if (personalDataDTO.experiences() != null && !personalDataDTO.experiences().isEmpty()) {
            List<Experience> experiences = personalDataDTO.experiences().stream()
                    .map(experienceId -> {
                        Experience experience = new Experience();
                        experience.setId(experienceId);
                        return experience;
                    })
                    .collect(Collectors.toList());
            personalData.setExperiences(experiences);
        } else {
            personalData.setExperiences(null);
        }
    
        if (personalDataDTO.formations() != null && !personalDataDTO.formations().isEmpty()) {
            List<Formation> formations = personalDataDTO.formations().stream()
                    .map(formationId -> {
                        Formation formation = new Formation();
                        formation.setId(formationId);
                        return formation;
                    })
                    .collect(Collectors.toList());
            personalData.setFormations(formations);
        } else {
            personalData.setFormations(null);
        }
    }

    private PersonalDataDTO convertToDTO(PersonalData personalData) {
        return new PersonalDataDTO(
            personalData.getId(), 
            personalData.getName(), 
            personalData.getContactEmail(),
            personalData.getGithubLink(),
            personalData.getLinkedinLink(),
            personalData.getDisability(),
            personalData.getDisabilityDescription(),
            personalData.getBirthDate(), 
            personalData.getUser().getId(),
            personalData.getDiversity() != null ? personalData.getDiversity().getId() : null,
            personalData.getCourses().getId(),
            personalData.getExperiences() != null ? personalData.getExperiences().stream().map(Experience::getId).collect(Collectors.toList()) : Collections.emptyList(), 
            personalData.getFormations() != null ? personalData.getFormations().stream().map(Formation::getId).collect(Collectors.toList()) : Collections.emptyList()
        );
    }
    public PersonalData findById(Long id) {
        return personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found: " + id));
    }
}