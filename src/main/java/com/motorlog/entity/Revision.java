package com.motorlog.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Entity
@Access(AccessType.PROPERTY)
public class Revision extends DomainEntity {

    //Attributes
    private String personalInfo;
    private Date entryDate;
    private Date departureDate;
    private String comments;
    private Double labourPrice;
    private Double itemsPrice;
    private Double finalPrice;
    private Date nextRevision;
    private Collection<Boolean> isSubstituted;

    //Relationships

    private Garage garage;
    private Vehicle vehicle;

    //Getters

    @Length(max = 500)
    public String getPersonalInfo() {
        return this.personalInfo;
    }
    @Past
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getEntryDate() {
        return this.entryDate;
    }

    //@Future
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getDepartureDate() {
        return this.departureDate;
    }

    @NotBlank
    @Length(max = 700)
    public String getComments() {
        return this.comments;
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

    //TODO Coger la siguiente fecha a la actual del manual de revisiones(?)
    //@Future
    public Date getNextRevision(){
        return this.nextRevision;
    }

    @Valid
    @ManyToOne()
    public Garage getGarage() {
        return garage;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Vehicle getVehicle() {
        return vehicle;
    }

    @ElementCollection
    //@NotEmpty
    public Collection<Boolean> getIsSubstituted() {
        return this.isSubstituted;
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
    public void setComments(String comments) {
        this.comments = comments;
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
    public void setNextRevision(Date nextRevision) {
        this.nextRevision = nextRevision;
    }
    public void setGarage(Garage garage) {
        this.garage = garage;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void setIsSubstituted(Collection<Boolean> isSubstituted) {
        this.isSubstituted = isSubstituted;
    }
}
