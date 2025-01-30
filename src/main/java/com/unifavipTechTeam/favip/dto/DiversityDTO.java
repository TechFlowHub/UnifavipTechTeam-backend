package com.unifavipTechTeam.favip.dto;

import com.unifavipTechTeam.favip.entity.PersonalData;

public record DiversityDTO(Long id, String name, String gender, String sex, String race, String sexualOrientation, PersonalData personalData) {
    
}
