package com.motorlog.entity;

import javax.persistence.Entity;

@Entity
public class Garage extends Actor{

    //Attributes

    private String customer;

    //Getters

    public String getCustomer() {
        return customer;
    }

    //Setters

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
