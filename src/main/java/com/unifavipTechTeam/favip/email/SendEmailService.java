package com.unifavipTechTeam.favip.email;

import com.unifavipTechTeam.favip.entity.RecoveryCode;
import com.unifavipTechTeam.favip.repositories.RecoveryCodeRepository;
import com.unifavipTechTeam.favip.service.CryptoService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class SendEmailService {

    private final JavaMailSender javaMailSender;
    private final RecoveryCodeRepository recoveryCodeRepository;

    public SendEmailService(JavaMailSender javaMailSender, RecoveryCodeRepository recoveryCodeRepository) {
        this.javaMailSender = javaMailSender;
        this.recoveryCodeRepository = recoveryCodeRepository;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789<!@#$%&>";
    private static final SecureRandom RANDOM = new SecureRandom();

    private String generateRecoveryCode() {
        StringBuilder code = new StringBuilder(24);
        for (int i = 0; i < 24; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    public void sendFirstAccessCode(String to) {
        try {
            String recoveryCode = generateRecoveryCode();
            String encryptedCode = CryptoService.encrypt(recoveryCode);

            Optional<RecoveryCode> existingRecoveryCodeOpt = recoveryCodeRepository.findByEmail(to);

            RecoveryCode recoveryCodeEntity;
            if (existingRecoveryCodeOpt.isPresent()) {
                recoveryCodeEntity = existingRecoveryCodeOpt.get();
                recoveryCodeEntity.setCode(encryptedCode);
                recoveryCodeEntity.setCreatedAt(LocalDateTime.now());
            } else {
                recoveryCodeEntity = new RecoveryCode();
                recoveryCodeEntity.setEmail(to);
                recoveryCodeEntity.setCode(encryptedCode);
                recoveryCodeEntity.setCreatedAt(LocalDateTime.now());
            }

            recoveryCodeRepository.save(recoveryCodeEntity);

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(to);
            email.setSubject("First Access Code");
            email.setText("This is your first access code: " + recoveryCode);
            javaMailSender.send(email);

            System.out.println("Email sent with recovery code");
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt recovery code", e);
        }
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        javaMailSender.send(email);

        System.out.println("e-mail sent.");
    }
}