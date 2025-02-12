package com.unifavipTechTeam.favip.email;

import com.unifavipTechTeam.favip.entity.User;
import com.unifavipTechTeam.favip.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final SendEmailService sendEmailService;
    private final UserRepository userRepository;

    public EmailController(SendEmailService sendEmailService, UserRepository userRepository) {
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestBody EmailDto emailDto) {
        sendEmailService.sendEmail(emailDto.to(), emailDto.subject(), emailDto.text());
        return ResponseEntity.ok("Email sent");
    }

    @PostMapping("/send-first-acess-code")
    public ResponseEntity<String> sendFirstAcessCode(@RequestBody EmailDto emailDto) {
        Optional<User> optionalUser = userRepository.findByEmail(emailDto.to());
        if(optionalUser.isPresent()){
            sendEmailService.sendFirstAccessCode(emailDto.to());
            return ResponseEntity.ok("Email sent");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("something went wrong");
    }
}