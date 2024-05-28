package com.agencia.pf.services;

import com.agencia.pf.models.EmailObject;
import com.agencia.pf.models.mailsmasivos.IEmailService;
import com.agencia.pf.models.mailsmasivos.UsuarioSMTPMailConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements IEmailService {

    public JavaMailSender getJavaMailSender(String emailUser, String password) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(emailUser);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "*");

        return mailSender;
    }

    @Override
    public void sendEmail(EmailObject emailObject,  UsuarioSMTPMailConfig usuarioSMTPMailConfig) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailObject.getFrom());

        mailMessage.setTo((emailObject.getTo()==null)? emailObject.getLto() : null);
        mailMessage.setBcc((emailObject.getCco()==null)? emailObject.getLcco() : null);
        mailMessage.setSubject(emailObject.getSubject());
        mailMessage.setText(emailObject.getBody());

        this.getJavaMailSender(usuarioSMTPMailConfig.getUsuariomail(),usuarioSMTPMailConfig.getPassword()).send(mailMessage);
    }

}
