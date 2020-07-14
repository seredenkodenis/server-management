package com.serverside.servermanagement.Controllers;

import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Repos.ProcessRepo;
import com.serverside.servermanagement.Service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class Admin {
    @Autowired
    private ProcessService processService;
    @Autowired
    private ProcessRepo processRepo;

    @GetMapping("/")
    public String allInOne(){
        return "main-admin";
    }

    @GetMapping("/process-setter")
    public String processSetter(Model model) throws IOException {
        List<Proc> procList = (List<Proc>) processRepo.findAll();
        for(int i = 0; i< procList.size();++i){
            processService.refresh(procList.get(i));
        }
        Iterable<Proc> procs = processRepo.findAll();
        model.addAttribute("processes", procs);
        return "processSetter";
    }

    @PostMapping("/process-setter")
    public String addProc(@RequestParam Integer pid) throws IOException {
        Proc proc = new Proc();
        proc.setPid(pid);
        processService.addName(proc);
        processService.addTime(proc);
        processRepo.save(proc);
        return "redirect:/admin/process-setter";
    }
}
