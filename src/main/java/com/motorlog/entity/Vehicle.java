package com.motorlog.entity;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Vehicle extends DomainEntity{

    //Attributes

    private VehicleType     type;
    private String          licensePlate;
    private String          brand;
    private String          model;
    private String          color;
    private String          chassisNumber;
    private Double          cc;
    private Integer         km;
    private Integer         registrationDate;
    private Date            nextRevision;
    private String          picture;

    //Relationships

    private Manual manual;

    //Getters

    @NotNull
    public VehicleType getType() {
        return this.type;
    }

    @NotBlank
    @NotNull
    @Column(unique = true)
    public String getLicensePlate() {
        return this.licensePlate;
    }

    @NotBlank
    @NotNull
    public String getBrand() {
        return this.brand;
    }

    @NotBlank
    @NotNull
    public String getModel() {
        return this.model;
    }

    public String getColor() {
        return this.color;
    }

    public String getChassisNumber() {
        return this.chassisNumber;
    }

    @Min(0)
    public Double getCc() {
        return this.cc;
    }

    @Min(0)
    public Integer getKm() {
        return this.km;
    }

    @Range(min=1880, max= 2019)
    public Integer getRegistrationDate() {
        return this.registrationDate;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getNextRevision() {
        return nextRevision;
    }

    @Valid
    @ManyToOne
    public Manual getManual() {
        return manual;
    }

    @URL
    public String getPicture() {
        return picture;
    }

    //Setters

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public void setCc(Double cc) {
        this.cc = cc;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public void setRegistrationDate(Integer registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setNextRevision(Date nextRevision) {
        this.nextRevision = nextRevision;
    }

    public void setManual(Manual manual) {
        this.manual = manual;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
