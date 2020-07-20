package com.serverside.servermanagement.Schedulers;

import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Entitiy.Service;
import com.serverside.servermanagement.Repos.ServiceRepo;
import com.serverside.servermanagement.Service.serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
public class scheduleService {
    @Autowired
    private serviceService serviceService;
    @Autowired
    private ServiceRepo serviceRepo;

    @Scheduled(cron = "0 * * * * *")
    public void checkService(){
        System.out.println("COOLOL");
        List<Service> serviceList = serviceRepo.findAll();
        for(int i = 0; i < serviceList.size(); ++i){
            ProcessBuilder processBuilder = new ProcessBuilder();
            System.out.println(serviceList.get(i).getName());
            processBuilder.command("bash","-c","/home/denis/IdeaProjects/server-management/src/main/resources/scripts/checkService.sh " + serviceList.get(i).getName());
            try {
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Service service = serviceRepo.findByName(serviceList.get(i).getName());
            String s1 = service.getEmail();
            System.out.println(s1);
            if(s1 != null) {
                System.out.println("sending!");
                    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
                    mailSender.setHost("mail.denis-seredenko.com");
                    mailSender.setPort(587);
                    mailSender.setUsername("admin@denis-seredenko.com");
                    mailSender.setPassword("seredenko302003");

                    Properties props = mailSender.getJavaMailProperties();
                    props.put("mail.transport.protocol", "smtp");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.debug", "true");

                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setFrom("admin@denis-seredenko.com");
                    mailMessage.setTo("denis-seredenko@ukr.net");
                    mailMessage.setSubject("Service id dead!");
                    mailMessage.setText("Hi, service with name: " + serviceList.get(i).getName() + " is dead!");
                    mailSender.send(mailMessage);
                    service.setEmail(null);
                    serviceRepo.save(service);
            }
        }
    }
}
