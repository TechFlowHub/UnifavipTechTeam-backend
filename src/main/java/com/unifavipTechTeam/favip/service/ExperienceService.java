package com.unifavipTechTeam.favip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unifavipTechTeam.favip.dto.ExperienceDTO;
import com.unifavipTechTeam.favip.entity.Experience;
import com.unifavipTechTeam.favip.entity.PersonalData;
import com.unifavipTechTeam.favip.repositories.ExperienceRepository;
import com.unifavipTechTeam.favip.repositories.PersonalDataRepository;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final PersonalDataRepository personalDataRepository;

    public ExperienceService(ExperienceRepository experienceRepository, PersonalDataRepository personalDataRepository) {
        this.experienceRepository = experienceRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public List<ExperienceDTO> getAllExperience() {
        return experienceRepository.findAll().stream().map(this::convertExperienceToDTO).collect(Collectors.toList());
    }

    public ExperienceDTO getExperienceById(Long id) {
        return experienceRepository.findById(id).map(this::convertExperienceToDTO).orElse(null);
    }

    public String postExperience(ExperienceDTO experienceDTO) {
        Experience experience = new Experience();
        mapExperience(experience, experienceDTO);
        experienceRepository.save(experience);
        return "Experience for personal data " + experienceDTO.personalDataId() + " added successfully";
    }

    public String updateExperience(Long id, ExperienceDTO experienceDTO) {
        Experience experience = experienceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Experience not found: " + id));
        mapExperience(experience, experienceDTO);
        experienceRepository.save(experience);
        return "Experience id: " + id + " updated successfully";
    }

    public Boolean deleteExperience(Long id) {
        Experience experience = experienceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Experience not found: " + id));
        experienceRepository.delete(experience);
        return true;
    }

    public void mapExperience(Experience experience, ExperienceDTO experienceDTO) {
        experience.setPosition(experienceDTO.position());
        experience.setCompany(experienceDTO.company());
        experience.setStartDate(experienceDTO.startDate());
        experience.setEndDate(experienceDTO.endDate());
        experience.setDescription(experienceDTO.description());
        
        PersonalData personalData = personalDataRepository.findById(experienceDTO.personalDataId())
            .orElseThrow(() -> new RuntimeException("Personal data not found: " + experienceDTO.personalDataId()));
        experience.setPersonalData(personalData);
    }

    public ExperienceDTO convertExperienceToDTO(Experience experience) {
        return new ExperienceDTO(
            experience.getId(),
            experience.getPosition(),
            experience.getCompany(),
            experience.getStartDate(),
            experience.getEndDate(),
            experience.getDescription(),
            experience.getPersonalData().getId());
    }
}
