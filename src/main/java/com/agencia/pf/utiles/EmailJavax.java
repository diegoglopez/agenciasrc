package com.agencia.pf.utiles;

import java.util.*;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;

public class EmailJavax {

    final String username = "diego9966@gmail.com";
    final String password = "Ingreso900";
//    Session session;

    public EmailJavax() {
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true"); //TLS
//        session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
    }

    public void enviar(){
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("diego9966@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("diego9966@gmail.com")
//            );
//            // Set Subject: header field
//            message.setSubject("Testing mails");
//
//            // Create the message part
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent(message, "text/html");
//            // Now set the actual message
//            messageBodyPart.setText("// message contains HTML markups\n" +
//                    "        String message = \"<i>Greetings!</i><br>\";\n" +
//                    "        message += \"<b>Wish you a nice day!</b><br>\";\n" +
//                    "        message += \"<font color=red>Duke</font>\";\n" +
//                    " \n" +
//                    "        SendEmailTLS mailer = new SendEmailTLS();");
//
//            // Create a multipar message
//            Multipart multipart = new MimeMultipart();
//
//            // Set text message part
//            multipart.addBodyPart(messageBodyPart);
//
////         // Part two is attachment
//            messageBodyPart = new MimeBodyPart();
//            // String filename = "C:\\Users\\Leonardo Castaño\\Documents\\NetBeansProjects\\escrow_repo\\web\\files\\uploads\\Libre de deuda_10784263_INTERBANK.pdf";
//            // DataSource source = new FileDataSource(filename);
//            // messageBodyPart.setDataHandler(new DataHandler(source));
//            // messageBodyPart.setFileName(filename);
//            multipart.addBodyPart(messageBodyPart);
//
//            // Send the complete message parts
//            message.setContent(multipart);
//
//            // Send message
//            Transport.send(message);
//
//            System.out.println("Sent message successfully....");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }


}