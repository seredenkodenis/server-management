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
        return "redirect:/admin/diagnosticDisk";
    }

    @RequestMapping(path = "/diagnostic/checkHacking", method = RequestMethod.GET)
    public ResponseEntity<Resource> checkHacking() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "checkRootKit.sh " + env.getProperty("sudoPass"));
        Process process = processBuilder.start();
        process.waitFor();
        File file = new File(env.getProperty("pathToResults")+"checkRootKit.txt");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/diagnostic/setDisk")
    public String seterDisk(){
        List<Disk> diskList = diskRepo.findAll();
        if(diskList.size() == 0) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", env.getProperty("pathToScripts") + "setDisk.sh " + env.getProperty("sudoPass"));
            try {
                processBuilder.start();
                System.out.println("GONE");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin/diagnosticDisk";
    }



    @GetMapping("/diagnostic/diskChecker/{report}")
    public String diagnosticDisk(@PathVariable("report") Integer report) throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c",env.getProperty("pathToScripts") + "checkDisk.sh " + env.getProperty("sudoPass"));
        Process process = processBuilder.start();
        process.waitFor();
        if(report == 1){
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
}
