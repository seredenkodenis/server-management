package com.serverside.servermanagement.Entitiy;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Log {
    @Id
    @GeneratedValue
    Integer id;

    String name;

    String description;

    Date date;

    @ManyToOne
    @JoinColumn(name = "PROCESS_NAME")
    Proc process;

    public Log() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proc getProcess() {
        return process;
    }

    public void setProcess(Proc process) {
        this.process = process;
    }
}
