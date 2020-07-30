package com.serverside.servermanagement.Schedulers;

import com.serverside.servermanagement.Entitiy.Service;
import com.serverside.servermanagement.Repos.ServiceRepo;
import com.serverside.servermanagement.Service.ProcessService;
import com.serverside.servermanagement.Service.serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class scheduleService {
    @Autowired
    private com.serverside.servermanagement.Mailer mailer;

    @Autowired
    private serviceService serviceService;
    @Autowired
    private ServiceRepo serviceRepo;

    @Scheduled(cron = "${cronService}")
    public void checkService(){
        System.out.println("qwe");
        List<Service> serviceList = serviceRepo.findAll();
        for(int i = 0; i < serviceList.size(); ++i){
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash","-c","/home/denis/IdeaProjects/server-management/src/main/resources/scripts/checkService.sh " + serviceList.get(i).getName());
            try {
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Service service = serviceRepo.findByName(serviceList.get(i).getName());
            String s1 = service.getEmail();
            if(s1 == null) {
                    mailer.serviceIsDead("Service is dead!",serviceList.get(i).getName());
                    service.setEmail(null);
                    serviceRepo.save(service);
                    serviceService.setLogDead(serviceList.get(i));
                    continue;
            }
            serviceService.setLog(serviceList.get(i));
        }
    }
}
