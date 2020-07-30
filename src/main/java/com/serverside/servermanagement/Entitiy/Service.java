package com.serverside.servermanagement.Entitiy;

import javax.persistence.*;
import java.util.List;

@Entity
public class Service {
    @Id
    @Column(name = "S_NAME")
    String name;

    @Column
    String command;

    @Column
    String activity;

    @Column
    String gGroup;

    @Column
    String status;
    @Column
    String email;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogServices> logs;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getgGroup() {
        return gGroup;
    }

    public void setgGroup(String gGroup) {
        this.gGroup = gGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LogServices> getLogs() {
        return logs;
    }

    public void setLogs(List<LogServices> logs) {
        this.logs = logs;
    }
}
