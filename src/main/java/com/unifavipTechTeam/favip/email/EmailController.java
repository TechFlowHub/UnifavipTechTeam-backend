package com.unifavipTechTeam.favip.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
public class EmailController {

    private final SendEmailService sendEmailService;

    @Value("${email.api.token}")
    private String emailApiToken;

    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestHeader("X-Email-Token") String token,
            @RequestBody EmailDto emailDto) {

        if (!emailApiToken.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: Invalid token");
        }

        sendEmailService.sendEmail(emailDto.to(), emailDto.subject(), emailDto.text());
        return ResponseEntity.ok("Email sent");
    }
}
