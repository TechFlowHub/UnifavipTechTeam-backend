package com.unifavipTechTeam.favip.dto;

import com.unifavipTechTeam.favip.email.EmailRoleValidation;

@EmailRoleValidation
public record RegisterRequestDto (String email, String password, String role){
}
