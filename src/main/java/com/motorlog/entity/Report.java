package com.motorlog.entity;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity{

    //Attributes

    private Boolean isBug;
    private Status status;
    private String title;
    private String content;
    private String photo;
    private Date moment;

    //Relationships

    private Garage garage;

    //Getters

    public Boolean getBug() {
        return isBug;
    }

    @Valid
    @NotNull
    public Status getStatus() {
        return this.status;
    }

    @NotBlank
    public String getTitle() {
        return this.title;
    }

    @NotBlank
    public String getContent() {
        return this.content;
    }

    @NotBlank
    @URL
    public String getPhoto() {
        return this.photo;
    }

    @Past
    public Date getMoment() {
        return this.moment;
    }

    @Valid
    @ManyToOne()
    public Garage getGarage(){
        return this.garage;
    }

    //Setters

    public void setBug(Boolean bug) {
        this.isBug = bug;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public void setMoment(Date moment) {
        this.moment = moment;
    }
    public void setGarage(Garage garage){
        this.garage = garage;
    }

}
