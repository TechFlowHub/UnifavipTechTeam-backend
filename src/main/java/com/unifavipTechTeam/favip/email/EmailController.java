package com.unifavipTechTeam.favip.email;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
public class EmailController {
    private SendEmailService sendEmailService;

    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailDto emailDto) {
        sendEmailService.sendEmail(emailDto.to(), emailDto.subject(), emailDto.text()   );
        return "Email sent";
    }


}
