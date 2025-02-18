package com.unifavipTechTeam.favip.dto;

import jakarta.validation.constraints.NotNull;

public record StarRequestDto(
        @NotNull(message = "O ID do doador (giverId) é obrigatório.") Long giverId,
        @NotNull(message = "O ID do receptor (receiverId) é obrigatório.") Long receiverId
) {}
