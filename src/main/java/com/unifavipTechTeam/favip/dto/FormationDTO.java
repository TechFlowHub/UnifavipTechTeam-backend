package com.unifavipTechTeam.favip.dto;

import java.sql.Date;

import com.unifavipTechTeam.favip.entity.PersonalData;

public record FormationDTO(Long id, String course, String institution, String level, Date startDate, Date endDate, PersonalData personalData) {
    
}
