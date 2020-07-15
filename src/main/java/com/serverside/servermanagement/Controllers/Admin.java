package com.serverside.servermanagement.Controllers;

import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Entitiy.System;
import com.serverside.servermanagement.Repos.ProcessRepo;
import com.serverside.servermanagement.Repos.SystemRepo;
import com.serverside.servermanagement.Service.ProcessService;
import com.serverside.servermanagement.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class Admin {
    @Autowired
    private ProcessService processService;
    @Autowired
    private ProcessRepo processRepo;

    @Autowired
    private SystemRepo systemRepo;

    @Autowired
    private SystemService systemService;

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

    @GetMapping("/delete/process-delete/{pid}")
    public String deleteProc(@PathVariable("pid") Integer pid){
        Proc proc = processRepo.findProcByPid(pid);
        processRepo.delete(proc);
        return "redirect:/admin/process-setter";
    }

    @GetMapping("/checkInfo/process/{pid}")
    public String checkProcess(@PathVariable("pid") Integer pid, Model model){
        Proc proc = processRepo.findProcByPid(pid);
        processService.refreshInfo(proc);
        Proc proc2 = processRepo.findProcByPid(pid);
        model.addAttribute("process", proc2);
        return "processInfo";
    }

    @GetMapping("/checkSystemInfo")
    public String checkSystem(Model model){
        System system = systemRepo.getOne(1);
        systemService.checkAllSystem(system);
        return "redirect:/admin/process-setter";
    }

}
