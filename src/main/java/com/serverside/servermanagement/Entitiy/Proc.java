package com.serverside.servermanagement.Entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proc {
    @Column
    Integer pid;

    @Id
    @GeneratedValue
    @Column(name = "PROCESS_NAME")
    public String name;

    @Column
    String status;

    @Column
    String uptime;

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


}
