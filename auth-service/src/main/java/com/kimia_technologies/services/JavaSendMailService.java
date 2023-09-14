package com.kimia_technologies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JavaSendMailService {
    private final JavaMailSender mailSender;
    public void sendEmail(String toEmail, String token){
        String body = "Bonjour Mr/Mme/Mlle \n " +
                "Ce mail que l'équipe technique vous adresse, est une correspondance confidentielle. \n" +
                "Ce mail contient un token de validation du mot de votre mot de passe." +
                "Merci, connecter vous à votre boite mail, afin de renitialiser votre mot de passe cliquer sur le lien pour la reinitialisation \n" +
                "Email: "+toEmail+" \n" +
                "token: "+token;

        String subject = "Réinitialisation du mot de passe";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ndjogdesire@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }
}
