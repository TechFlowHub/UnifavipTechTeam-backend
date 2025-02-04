package com.unifavipTechTeam.favip.dto;

import com.unifavipTechTeam.favip.entity.Enums.*;

public record DiversityDTO(
    Long id,
    String socialName,
    Gender gender,
    Sex sex,
    Race race,
    SexualOrientation sexualOrientation,
    Long personalData
) {}