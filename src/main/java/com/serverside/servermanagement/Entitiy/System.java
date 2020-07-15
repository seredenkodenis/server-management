package com.serverside.servermanagement.Entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class System {
    @Id
    Integer id;

    @Column
    String name;

    @Column(name = "memory")
    String memory;

    @Column(name = "av_memory")
    String availableMemory;

    @Column
    String nucleus;

    @Column(name = "arch")
    String arch;

    @Column
    String uptime;

    @Column
    String reboot;

    @Column(name = "cpuName")
    String cpuName;

    @Column
    String shutdown;

    @Column(name = "distrVersion")
    String distrVersion;

    @Column(name = "nuclearVers")
    String nuclearVers;

    public System() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getNucleus() {
        return nucleus;
    }

    public void setNucleus(String nucleus) {
        this.nucleus = nucleus;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getReboot() {
        return reboot;
    }

    public void setReboot(String reboot) {
        this.reboot = reboot;
    }

    public String getShutdown() {
        return shutdown;
    }

    public void setShutdown(String shutdown) {
        this.shutdown = shutdown;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getNuclearVers() {
        return nuclearVers;
    }

    public void setNuclearVers(String nuclearVers) {
        this.nuclearVers = nuclearVers;
    }

    public String getDistrVersion() {
        return distrVersion;
    }

    public void setDistrVersion(String distrVersion) {
        this.distrVersion = distrVersion;
    }

    public String getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(String availableMemory) {
        this.availableMemory = availableMemory;
    }
}
