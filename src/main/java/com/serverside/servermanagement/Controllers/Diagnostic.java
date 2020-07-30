package com.serverside.servermanagement.Controllers;

import com.serverside.servermanagement.Entitiy.Disk;
import com.serverside.servermanagement.Entitiy.Proc;
import com.serverside.servermanagement.Repos.DiskRepo;
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
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class Diagnostic {
    @Autowired
    private Environment env;

    @Autowired
    private DiskRepo diskRepo;

    @GetMapping("/diagnostic")
    public String diagnosticMainPage1(Model model){
        return "diagnosticMenu";
    }

    @GetMapping("/diagnosticDisk")
    public String diagnosticMainPage(Model model){
        List<Disk> diskList = diskRepo.findAll();
        if(diskList.size() != 0) {
            Disk disk = diskRepo.getOne(1);
            model.addAttribute("disk", disk);
            return "diagnosticDisk";
        }
        return "redirect:/admin/diagnostic";
    }

    @RequestMapping(path = "/diagnostic/checkHacking", method = RequestMethod.GET)
    public ResponseEntity<Resource> checkHacking() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "checkRootKit.sh " + env.getProperty("sudoPass") + " " + env.getProperty("pathToResults") + "/checkRootKit.txt");
        Process process = processBuilder.start();
        process.waitFor();
        File file = new File(env.getProperty("pathToResults")+"checkRootKit.txt");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @RequestMapping(path = "/diagnostic/getPartition", method = RequestMethod.GET)
    public ResponseEntity<Resource> getPartition() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","ls /dev/sd* >> " + env.getProperty("pathToResults")+"partition.txt");
        Process process = processBuilder.start();
        process.waitFor();
        File file = new File(env.getProperty("pathToResults")+"partition.txt");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @RequestMapping(path = "/diagnostic/getHackCheck", method = RequestMethod.GET)
    public ResponseEntity<Resource> getHackCheck() throws IOException, InterruptedException {
        File file = new File(env.getProperty("pathToResults")+"checkRootKit.txt");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/diagnostic/setDisk")
    public String seterDisk(@RequestParam String partition){
        List<Disk> diskList = diskRepo.findAll();
        System.out.println("kek");
        if(diskList.size() == 0) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "setDisk.sh " + env.getProperty("sudoPass") + " " + partition);
            System.out.println(env.getProperty("pathToScripts") + "setDisk.sh " + env.getProperty("sudoPass") + " " + partition);
            try {
                Process process = processBuilder.start();
                process.waitFor();
                System.out.println("GONE");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin/diagnosticDisk";
    }
    @GetMapping("/diagnostic/setRootKitChecker")
    public String checkRootSet() throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c", env.getProperty("pathToScripts") + "setUpRootKitChecker.sh");
        Process process = processBuilder.start();
        process.waitFor();
        return "redirect:/admin/diagnostic";
    }



    @GetMapping("/diagnostic/diskChecker")
    public String diagnosticDisk(@RequestParam("partition") String partition,@RequestParam("type") Integer type) throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "checkDisk.sh " + env.getProperty("sudoPass") + " " + partition + " " + env.getProperty("pathToResults")+"/diskReport.txt");
        Process process = processBuilder.start();
        process.waitFor();
        if(type == 1){
            return "redirect:/admin/diagnostic/diskReport";
        }
        return "redirect:/admin/diagnostic";
    }

    @RequestMapping(path = "/diagnostic/diskReport", method = RequestMethod.GET)
    public ResponseEntity<Resource> checkServicesErrors() throws IOException, InterruptedException {
        File file = new File(env.getProperty("pathToResults")+"diskReport.txt");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    @RequestMapping(path = "/diagnostic/logFile", method = RequestMethod.GET)
    public ResponseEntity<Resource> logFile() throws IOException, InterruptedException {
        File file = new File(env.getProperty("pathToResults")+"app.log");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
