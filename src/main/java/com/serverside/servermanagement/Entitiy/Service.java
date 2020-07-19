package com.serverside.servermanagement.Entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Service {
    @Id
    String name;

    @Column
    String command;

    @Column
    String activity;

    @Column
    String gGroup;

    @Column
    String status;

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
}
