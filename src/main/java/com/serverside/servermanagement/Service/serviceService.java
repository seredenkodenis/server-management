package com.serverside.servermanagement.Service;

import com.serverside.servermanagement.Entitiy.Log;
import com.serverside.servermanagement.Entitiy.LogServices;
import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Repos.LogServiceRepo;
import com.serverside.servermanagement.Repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class serviceService {
    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private LogServiceRepo logServiceRepo;
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

    public void setLog(com.serverside.servermanagement.Entitiy.Service service){
        LogServices logServices = new LogServices();
        Date date = new Date();
        logServices.setDate(date);
        logServices.setName("checked");
        logServices.setDescription("service check is done!");
        logServices.setService(service);
        logServiceRepo.save(logServices);
    }
    public void setLogDead(com.serverside.servermanagement.Entitiy.Service service){
        LogServices logServices = new LogServices();
        Date date = new Date();
        logServices.setDate(date);
        logServices.setName("dead");
        logServices.setDescription("service is dead!");
        logServices.setService(service);
        logServiceRepo.save(logServices);
    }


}
