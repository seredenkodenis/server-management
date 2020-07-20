package com.serverside.servermanagement.Schedulers;

import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Repos.ProcessRepo;
import com.serverside.servermanagement.Service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
public class scheduleProc {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProcessRepo processRepo;

    @Autowired
    private ProcessService processService;

    @Scheduled(cron = "0 * * * * *")
    public void checkProc(){
        List<Proc> procList = (List<Proc>) processRepo.findAll();
        for(int i = 0; i < procList.size(); ++i){
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "/home/denis/IdeaProjects/server-management/src/main/resources/scripts/checkProc.sh " + procList.get(i).getPid() + " denis-seredenko@ukr.net");
            try {
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Proc proc = processRepo.findProcByPid(procList.get(i).getPid());
            String s2 = proc.getEmail();
            if(s2!=null) {
                if (proc.getEmail().equals("send")) {
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
                    mailMessage.setSubject("Process id dead!");
                    mailMessage.setText("Hi, process with pid: " + procList.get(i).getPid() + " is dead!");
                    mailSender.send(mailMessage);
                    proc.setEmail(null);
                    processRepo.save(proc);
                }
            }
        }
    }
}
