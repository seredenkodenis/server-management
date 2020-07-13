package com.serverside.servermanagement.Entitiy;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Log {
    @Id
    @GeneratedValue
    Integer id;

    String name;

    String discription;

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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
