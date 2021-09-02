package com.mymailsenser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    @Autowired
    private MailSender mailSender;

    public void sendMail(){

        SimpleMailMessage msg = new SimpleMailMessage();


        msg.setFrom("devsemion@gmail.com");
        msg.setTo("devsemion@gmail.com");
        msg.setSubject("First mail message");

        msg.setText("Hello world!!!");

        mailSender.send(msg);
    }
}
