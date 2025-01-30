package com.unifavipTechTeam.favip.dto;

import java.sql.Date;
import java.util.List;

public record PersonalDataDTO(
        Long id,
        String name,
        String contactEmail,
        String githubLink,
        String linkedinLink,
        Boolean disability,
        String disabilityDescription,
        Date birthDate,
        Long user,
        Long diversity,
        Long course,
        List<Long> experiences,
        List<Long> formations
) {

}