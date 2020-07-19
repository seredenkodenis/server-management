package com.serverside.servermanagement.Service;

import com.serverside.servermanagement.Repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class serviceService {
    @Autowired
    private ServiceRepo serviceRepo;

    public void addService(com.serverside.servermanagement.Entitiy.Service service){
        serviceRepo.save(service);
    }
    public void startService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","/home/denis/IdeaProjects/server-management/src/main/resources/scripts/startService.sh"+ " " +  service.getName() + " seredenko302003");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","/home/denis/IdeaProjects/server-management/src/main/resources/scripts/stopService.sh"+ " " + service.getName() + " seredenko302003");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void statusService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","/home/denis/IdeaProjects/server-management/src/main/resources/scripts/statusService.sh"+  " " + service.getName() + " seredenko302003");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void restartService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","/home/denis/IdeaProjects/server-management/src/main/resources/scripts/restartService.sh"+ " " + service.getName()+ " seredenko302003");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteService(com.serverside.servermanagement.Entitiy.Service service){
         serviceRepo.delete(service);
    }


}
