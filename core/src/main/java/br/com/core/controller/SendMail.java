package br.com.core.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcelocaser
 */
@Service
public class SendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String from = "";

    public void sendMail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject(subject);
            message.setText(body);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
        } catch (MessagingException ex) {
            //tratar exceção
        }
        javaMailSender.send(message);
    }

}
