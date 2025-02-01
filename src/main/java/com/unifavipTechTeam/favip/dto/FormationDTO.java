package com.unifavipTechTeam.favip.dto;

import java.sql.Date;

import com.unifavipTechTeam.favip.entity.Enums.Level;

public record FormationDTO(Long id, String course, String institution, Level level, String description, Date startDate, Date endDate, Long personalData) {
    
}
