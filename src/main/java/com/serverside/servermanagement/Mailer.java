package com.serverside.servermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class Mailer {
    @Autowired
    private Environment env;

    public void serviceIsDead(String subject, String serviceName){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(env.getProperty("mailFrom"));
        mailMessage.setTo(env.getProperty("ownEmail"));
        mailMessage.setSubject(subject);
        mailMessage.setText("Hi, service with name: " + serviceName + " is dead!");
        mailSender.send(mailMessage);
    }

    public void processIsDead(String subject, Integer pid){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(env.getProperty("mailFrom"));
        mailMessage.setTo(env.getProperty("ownEmail"));
        mailMessage.setSubject(subject);
        mailMessage.setText("Hi, process with pid: " + pid + " is dead!");
        mailSender.send(mailMessage);
    }
}
