package com.motorlog.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Repair extends DomainEntity {

    //Attributes

    private String personalInfo;
    private Date entryDate;
    private Date departureDate;
    private String cause;
    private String actions;
    private Double labourPrice;
    private Double itemsPrice;
    private Double finalPrice;

    //Relationships

    private Garage garage;
    private Vehicle vehicle;

    //Getters

    @Length(max = 500)
    public String getPersonalInfo() {
        return this.personalInfo;
    }

    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getEntryDate() {
        return this.entryDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getDepartureDate() {
        return this.departureDate;
    }

    @NotBlank
    @Length(max = 700)
    public String getCause() {
        return this.cause;
    }

    @NotBlank
    @Length(max = 700)
    public String getActions() {
        return this.actions;
    }

    @Min(0)
    public Double getLabourPrice() {
        return this.labourPrice;
    }

    @Min(0)
    public Double getItemsPrice() {
        return this.itemsPrice;
    }
    //TODO Hacer que el finalPrice sea labourPrice+itemsPrice + 21% (hay que cogerlo de configuration.getVat()
    @Min(0)
    public Double getFinalPrice() {
            return this.finalPrice;
    }

    @Valid
    @ManyToOne()
    public Garage getGarage() {
        return this.garage;
    }

    @Valid
    @ManyToOne()
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    //Setters


    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
    public void setCause(String cause) {
        this.cause = cause;
    }
    public void setActions(String actions) {
        this.actions = actions;
    }
    public void setLabourPrice(Double labourPrice) {
        this.labourPrice = labourPrice;
    }
    public void setItemsPrice(Double itemsPrice) {
        this.itemsPrice = itemsPrice;
    }
    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
    public void setGarage(Garage garage) {
        this.garage = garage;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
