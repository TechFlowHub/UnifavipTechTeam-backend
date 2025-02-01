package com.unifavipTechTeam.favip.dto;

import java.sql.Date;

public record ExperienceDTO(Long id, String position, String company, Date startDate, Date endDate, String description, Long personalDataId) {
    
}
