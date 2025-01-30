package com.unifavipTechTeam.favip.email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.security.SecureRandom;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    private JavaMailSender javaMailSender;

    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    private String firstAcess(){
        StringBuilder code = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
    public void sendFirstAcessCode(String to){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject("CODIGO DE PRIMEIRO ACESSO");
        email.setText("este e o seu codigo de primeiro acesso a conta: " + firstAcess());
        javaMailSender.send(email);

        System.out.println("E-mail sent");
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        javaMailSender.send(email);

        System.out.println("E-mail sent");
    }


}
