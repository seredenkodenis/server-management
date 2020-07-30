package com.serverside.servermanagement.Entitiy;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LogServices {
    @Id
    @GeneratedValue
    Integer id;

    String name;

    String description;

    Date date;

    @ManyToOne
    @JoinColumn(name = "SERVICE_NAME")
    Service service;

    public LogServices() {
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
