package com.serverside.servermanagement.Controllers;

import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Service.ProcessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class Admin {
    @GetMapping("/")
    public String allInOne(){
        return "main-admin";
    }
    @GetMapping("/process-setter")
    public String processSetter() throws IOException {
        Proc proc = new Proc();
        proc.setPid(27323);
        ProcessService processService = new ProcessService();
        processService.addName(proc);
        processService.addTime(proc);
        return "processSetter";
    }

}
