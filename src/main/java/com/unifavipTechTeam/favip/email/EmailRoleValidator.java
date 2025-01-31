package com.unifavipTechTeam.favip.email;

import com.unifavipTechTeam.favip.dto.RegisterRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailRoleValidator implements ConstraintValidator<EmailRoleValidation, RegisterRequestDto> {

    @Override
    public boolean isValid(RegisterRequestDto request, ConstraintValidatorContext context) {
        if (request == null || request.email() == null || request.role() == null) {
            return true;
        }

        String email = request.email();
        String role = request.role();

        if ("student".equalsIgnoreCase(role) && !email.endsWith("@aluno.com")) {
            return false;
        }

        if ("teacher".equalsIgnoreCase(role) && !email.endsWith("@professor.com")) {
            return false;
        }

        return true;
    }
}
