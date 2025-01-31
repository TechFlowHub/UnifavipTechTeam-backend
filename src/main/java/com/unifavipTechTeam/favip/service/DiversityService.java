package com.unifavipTechTeam.favip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unifavipTechTeam.favip.dto.DiversityDTO;
import com.unifavipTechTeam.favip.entity.Diversity;
import com.unifavipTechTeam.favip.entity.PersonalData;
import com.unifavipTechTeam.favip.repositories.DiversityRepository;
import com.unifavipTechTeam.favip.repositories.PersonalDataRepository;

@Service
public class DiversityService {
    private final DiversityRepository diversityRepository;
    private final PersonalDataRepository personalDataRepository;

    public DiversityService(DiversityRepository diversityRepository, PersonalDataRepository personalDataRepository) {
        this.diversityRepository = diversityRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public List<DiversityDTO> getAllDiversity() {
        return diversityRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DiversityDTO getDiversityById(Long id) {
        return diversityRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public String postDiversity(DiversityDTO diversityDTO) {
        Diversity diversity = new Diversity();
        mapDiversity(diversity, diversityDTO);
        diversityRepository.save(diversity);
        return "Diversity for personal data " + diversityDTO.personalData() + " added successfully";
    }

    public String updateDiversity(Long id, DiversityDTO diversityDTO) {
        Diversity diversity = diversityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diversity not found: " + id));
        mapDiversity(diversity, diversityDTO);
        diversityRepository.save(diversity);
        return "Diversity id: " + id + " updated successfully";
    }

    public Boolean deleteDiversity(Long id) {
        Diversity diversity = diversityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diversity not found: " + id));
        diversityRepository.delete(diversity);
        return true;
    }

    private void mapDiversity(Diversity diversity, DiversityDTO diversityDTO) {
        diversity.setSocialName(diversityDTO.socialName());
        diversity.setGender(diversityDTO.gender());
        diversity.setSex(diversityDTO.sex());
        diversity.setRace(diversityDTO.race());
        diversity.setSexualOrientation(diversityDTO.sexualOrientation());
        
        PersonalData personalData = personalDataRepository.findById(diversityDTO.personalData())
                .orElseThrow(() -> new RuntimeException("Personal data not found: " + diversityDTO.personalData()));
        diversity.setPersonalData(personalData);
    }

    private DiversityDTO convertToDTO(Diversity diversity) {
        return new DiversityDTO(
            diversity.getId(), 
            diversity.getSocialName(), 
            diversity.getGender(), 
            diversity.getSex(),
            diversity.getRace(), 
            diversity.getSexualOrientation(), 
            diversity.getPersonalData().getId());
    }

}   
