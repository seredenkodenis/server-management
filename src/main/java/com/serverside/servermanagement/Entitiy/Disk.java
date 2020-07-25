package com.serverside.servermanagement.Entitiy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Disk {
    @Id
    Integer id;

    String device;

    String serialNumber;

    String capacity;

    String nameOfDisk;

    String partitions;

    String healthTest;

    public Disk() {
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getHealthTest() {
        return healthTest;
    }

    public void setHealthTest(String healthTest) {
        this.healthTest = healthTest;
    }

    public String getPartitions() {
        return partitions;
    }

    public void setPartitions(String partitions) {
        this.partitions = partitions;
    }

    public String getNameOfDisk() {
        return nameOfDisk;
    }

    public void setNameOfDisk(String nameOfDisk) {
        this.nameOfDisk = nameOfDisk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
