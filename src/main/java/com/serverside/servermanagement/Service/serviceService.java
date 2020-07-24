package com.serverside.servermanagement.Service;

import com.serverside.servermanagement.Repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class serviceService {
    @Autowired
    private ServiceRepo serviceRepo;
    @Autowired
    private Environment env;

    public void addService(com.serverside.servermanagement.Entitiy.Service service){
        serviceRepo.save(service);
    }
    public void startService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "startService.sh"+ " " +  service.getName() +" " + env.getProperty("sudoPass"));
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "stopService.sh"+ " " + service.getName() +" " + env.getProperty("sudoPass"));
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void statusService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        System.out.println(env.getProperty("pathToScripts"));
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "statusService.sh"+  " " + service.getName() + " " + env.getProperty("sudoPass"));
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void restartService(com.serverside.servermanagement.Entitiy.Service service){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "restartService.sh"+ " " + service.getName()+" " + env.getProperty("sudoPass"));
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
