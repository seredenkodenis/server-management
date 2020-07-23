package com.serverside.servermanagement.Service;

import com.serverside.servermanagement.Entitiy.System;
import com.serverside.servermanagement.Repos.SystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SystemService {

    @Autowired
    private Environment env;

    @Autowired
    private SystemRepo systemRepo;

    //add only one Time!
    public void startVision(){
        System system = new System();
        system.setId(1);
        systemRepo.save(system);
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "onceSystem.sh");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void checkAllSystem(System system){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "refreshServerInfo.sh");
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
