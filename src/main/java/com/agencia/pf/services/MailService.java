package com.agencia.pf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("diego9966@gmail.com");
        message.setSubject("Correo Test");
        message.setText("Correos Test");

        // Especificar el remitente del correo
        message.setFrom("diego_9966@hotmail.com");

        // Enviar el correo electrónico
        javaMailSender.send(message);

    }
}
