package com.serverside.servermanagement.Service;

import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Repos.ProcessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Service
public class ProcessService {
    @Autowired
    private ProcessRepo processRepo;

    @Autowired
    private Environment env;

    public void addName(Proc procer) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","ps -p "+procer.getPid()+" -o command");
        Process process1 = processBuilder.start();
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process1.getInputStream()));
        String line;
        line =  reader.readLine();
        procer.setName(line);
    }
    public void addTime(Proc procer) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","ps -p "+procer.getPid()+" -o etime=");
        Process process1 = processBuilder.start();
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process1.getInputStream()));
        String line;
        line =  reader.readLine();
        procer.setUpTime(line);
    }

    public void refresh(Proc procer){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "check.sh "+ procer.getPid() + " " + procer.getName());
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshInfo(Proc procer){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "refreshAllInfo.sh "+ procer.getPid() + " " + procer.getName());
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
