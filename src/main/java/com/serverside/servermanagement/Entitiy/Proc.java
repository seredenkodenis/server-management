package com.serverside.servermanagement.Entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proc {
    @Column
    Integer pid;

    @Id
    @Column(name = "PROCESS_NAME")
    public String name;

    @Column
    String status;

    @Column
    String uptime;

    @Column
    String author;

    @Column
    String mem;

    @Column
    String cpu;

    @Column
    String vsz;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Log> logs;

    public Proc() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpTime() {
        return uptime;
    }

    public void setUpTime(String starterTime) {
        this.uptime = starterTime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getVsz() {
        return vsz;
    }

    public void setVsz(String vsz) {
        this.vsz = vsz;
    }
}
