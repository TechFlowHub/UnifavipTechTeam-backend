package com.unifavipTechTeam.favip.email;

import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("email")
public class EmailController {

    private final SendEmailService sendEmailService;
    private final UserRepository userRepository;

    @Value("${email.api.token}")
    private String emailApiToken;

    public EmailController(SendEmailService sendEmailService, UserRepository userRepository) {
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
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

    @PostMapping("/sendFirstAcessCode")
    public ResponseEntity<String> sendFirstAcessCode(@RequestHeader("X-Email-Token")String token, @RequestBody EmailDto emailDto) {
        Optional<User> optionalUser = userRepository.findByEmail(emailDto.to());
        if (!emailApiToken.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: Invalid token");
        }
        if(optionalUser.isPresent()){
            sendEmailService.sendFirstAccessCode(emailDto.to());
            return ResponseEntity.ok("Email sent");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("algo errado");
    }
}