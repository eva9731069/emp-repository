package com.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService  {

    @Autowired
    private JavaMailSender mailSender;


    public void prepareAndSend(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(toEmail);
        message.setTo(toEmail,"eva9731069@yahoo.com.tw");
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("成功寄信");

    }

}