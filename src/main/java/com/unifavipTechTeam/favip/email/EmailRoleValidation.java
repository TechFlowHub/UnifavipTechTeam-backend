package com.unifavipTechTeam.favip.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailRoleValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailRoleValidation {
    String message() default "Invalid email for the given role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}