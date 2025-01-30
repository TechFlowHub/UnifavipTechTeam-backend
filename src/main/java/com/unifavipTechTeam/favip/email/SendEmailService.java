package com.unifavipTechTeam.favip.email;

import com.unifavipTechTeam.favip.entity.RecoveryCode;
import com.unifavipTechTeam.favip.repositories.RecoveryCodeRepository;
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

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private String generateRecoveryCode() {
        StringBuilder code = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    public void sendFirstAccessCode(String to) {
        String recoveryCode = generateRecoveryCode(); // Gera o código

        // Verifica se já existe um código para o email
        Optional<RecoveryCode> existingRecoveryCodeOpt = recoveryCodeRepository.findByEmail(to);

        RecoveryCode recoveryCodeEntity;
        if (existingRecoveryCodeOpt.isPresent()) {
            // Se existir, atualiza o código e o horário de criação
            recoveryCodeEntity = existingRecoveryCodeOpt.get();
            recoveryCodeEntity.setCode(recoveryCode);
            recoveryCodeEntity.setCreatedAt(LocalDateTime.now());
        } else {
            // Se não existir, cria um novo registro
            recoveryCodeEntity = new RecoveryCode();
            recoveryCodeEntity.setEmail(to);
            recoveryCodeEntity.setCode(recoveryCode);
            recoveryCodeEntity.setCreatedAt(LocalDateTime.now());
        }

        // Salva ou atualiza o código no banco de dados
        recoveryCodeRepository.save(recoveryCodeEntity);

        // Envia o email com o código
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject("Código de Primeiro Acesso");
        email.setText("Este é o seu código de primeiro acesso: " + recoveryCode);
        javaMailSender.send(email);

        System.out.println("E-mail enviado com o código de recuperação.");
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        javaMailSender.send(email);

        System.out.println("E-mail enviado.");
    }
}