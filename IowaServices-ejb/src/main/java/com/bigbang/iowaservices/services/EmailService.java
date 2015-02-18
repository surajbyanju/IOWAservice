/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.services;

import java.util.Properties;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dell
 */
@Stateless
@Local
public class EmailService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void serviceRequestEmail(String toEmail, String subject, String msgBody) {
        final String username = "iowaservice1@gmail.com";
        final String password = "iowaservice@1";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(msgBody);

            Transport.send(message);
            System.out.println("!!!!!!!!e-mail sent!!!!!!!!!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
