package com.unifavipTechTeam.favip.dto;

import com.unifavipTechTeam.favip.entity.Diversity;
import com.unifavipTechTeam.favip.entity.Courses;
import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.entity.Experience;
import com.unifavipTechTeam.favip.entity.Formation;

import java.util.List;

public record PersonalDataDTO(
        Long id,
        String name,
        String contactEmail,
        String githubLink,
        String linkedinLink,
        Boolean disability,
        String disabilityDescription,
        String birthDate,
        User user,
        Diversity diversity,
        Courses courses,
        List<Experience> experiences,
        List<Formation> formations
) {

}