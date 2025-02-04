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

        if ("admin".equalsIgnoreCase(role)) {
            return false;
        }

        if ("student".equalsIgnoreCase(role) && !email.endsWith("@alunos.unifavip.edu.br")) {
            return false;
        }

        if ("teacher".equalsIgnoreCase(role) && !email.endsWith("@professores.unifavip.edu.br")) {
            return false;
        }

        return true;
    }
}
