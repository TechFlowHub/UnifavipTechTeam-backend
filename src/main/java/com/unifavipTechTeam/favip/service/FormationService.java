package com.unifavipTechTeam.favip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unifavipTechTeam.favip.entity.Formation;
import com.unifavipTechTeam.favip.entity.PersonalData;
import com.unifavipTechTeam.favip.dto.FormationDTO;
import com.unifavipTechTeam.favip.repositories.FormationRepository;
import com.unifavipTechTeam.favip.repositories.PersonalDataRepository;

@Service
public class FormationService {
    private final FormationRepository formationRepository;
    private final PersonalDataRepository personalDataRepository;

    public FormationService(FormationRepository formationRepository, PersonalDataRepository personalDataRepository) {
        this.formationRepository = formationRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public List<FormationDTO> getAllFormation() {
        return formationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public FormationDTO getFormationById(Long id) {
        return formationRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public String postFormation(FormationDTO formationDTO) {
        Formation formation = new Formation();
        mapFormation(formation, formationDTO);
        formationRepository.save(formation);
        return "Formation for personal data " + formationDTO.personalData() + " added successfully";
    }

    public String updateFormation(Long id, FormationDTO formationDTO) {
        Formation formation = formationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Formation not found: " + id));
        mapFormation(formation, formationDTO);
        formationRepository.save(formation);
        return "Formation id: " + id + " updated successfully";
    }

    public Boolean deleteFormation(Long id) {
        Formation formation = formationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Formation not found: " + id));
        formationRepository.delete(formation);
        return true;
    }

    private void mapFormation(Formation formation, FormationDTO formationDTO) {
        formation.setCourse(formationDTO.course());
        formation.setInstitution(formationDTO.institution());
        formation.setLevel(formationDTO.level());
        formation.setDescription(formationDTO.description());
        formation.setStartDate(formationDTO.startDate());
        formation.setEndDate(formationDTO.endDate());

        PersonalData personalData = personalDataRepository.findById(formationDTO.personalData())
            .orElseThrow(() -> new RuntimeException("Personal data not found: " + formationDTO.personalData()));
        formation.setPersonalData(personalData);
    }

    private FormationDTO convertToDTO(Formation formation) {
        return new FormationDTO(
            formation.getId(), 
            formation.getCourse(), 
            formation.getInstitution(), 
            formation.getLevel(),
            formation.getDescription(), 
            formation.getStartDate(), 
            formation.getEndDate(), 
            formation.getPersonalData().getId());
    }
}
