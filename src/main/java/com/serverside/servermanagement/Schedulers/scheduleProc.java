package com.serverside.servermanagement.Schedulers;

import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Repos.ProcessRepo;
import com.serverside.servermanagement.Service.ProcessService;
import com.serverside.servermanagement.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class scheduleProc {
    @Autowired
    private Mailer mailer;

    @Autowired
    private ProcessRepo processRepo;

    @Autowired
    private ProcessService processService;

    @Scheduled(cron = "${cronProc}")
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
                    mailer.processIsDead("Process is dead",procList.get(i).getPid());
                    proc.setEmail(null);
                    processRepo.save(proc);
                    processService.setLogDead(procList.get(i));
                    continue;
                }
            }
            processService.setLog(procList.get(i));
        }
    }
}
