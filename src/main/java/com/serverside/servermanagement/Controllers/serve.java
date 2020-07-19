package com.serverside.servermanagement.Controllers;

import com.serverside.servermanagement.Entitiy.Service;
import com.serverside.servermanagement.Repos.ServiceRepo;
import com.serverside.servermanagement.Service.serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class serve {
    @Autowired
    private serviceService serviceService;

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
}
