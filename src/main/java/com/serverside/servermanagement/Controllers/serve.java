package com.serverside.servermanagement.Controllers;

import com.serverside.servermanagement.Entitiy.Service;
import com.serverside.servermanagement.Repos.ServiceRepo;
import com.serverside.servermanagement.Service.serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class serve {
    @Autowired
    private serviceService serviceService;

    @Autowired
    private Environment env;

    @Autowired
    private ServiceRepo serviceRepo;

    @GetMapping("/services")
    public String servicesMenu(Model model){
        List<Service> services = serviceRepo.findAll();
        model.addAttribute("services",services);
        return "services";
    }

    @GetMapping("/startService/{name}")
    public String starter(@PathVariable("name") String name){
        Service service = serviceRepo.findByName(name);
        serviceService.startService(service);
        return "redirect:/admin/services";
    }
    @GetMapping("/stopService/{name}")
    public String stoper(@PathVariable("name") String name){
        Service service = serviceRepo.findByName(name);
        serviceService.stopService(service);
        return "redirect:/admin/services";
    }
    @GetMapping("/restartService/{name}")
    public String restarter(@PathVariable("name") String name){
        Service service = serviceRepo.findByName(name);
        serviceService.restartService(service);
        return "redirect:/admin/services";
    }
    @GetMapping("/statusService/{name}")
    public String status(@PathVariable("name") String name){
        Service service = serviceRepo.findByName(name);
        serviceService.statusService(service);
        return "redirect:/admin/services";
    }
    @PostMapping("/addService")
    public String adder(@RequestParam String name){
        Service service = new Service();
        service.setName(name);
        serviceRepo.save(service);
        return "redirect:/admin/services";
    }
    @GetMapping("/deleteService/{name}")
    public String deleter(@PathVariable("name") String name){
        Service service = serviceRepo.findByName(name);
        serviceService.deleteService(service);
        return "redirect:/admin/services";
    }
    @RequestMapping(path = "/findAllServices", method = RequestMethod.GET)
    public ResponseEntity<Resource>  allServicesAtComputer() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "allServices.sh " + env.getProperty("pathToResults") + "/allServices.txt");
        Process process = processBuilder.start();
        process.waitFor();
        File file = new File(env.getProperty("pathToResults")+"allServices.txt");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    @RequestMapping(path = "/checkAllServicesErrors", method = RequestMethod.GET)
    public ResponseEntity<Resource>  checkServicesErrors() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "failedServices.sh " + env.getProperty("pathToResults") + "/failedServices.txt");
        Process process = processBuilder.start();
        process.waitFor();
        File file = new File(env.getProperty("pathToResults")+"failedServices.txt");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        System.out.println("LOL");
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
