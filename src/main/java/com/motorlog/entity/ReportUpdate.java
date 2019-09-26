package com.motorlog.entity;

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
public class ReportUpdate extends DomainEntity {

    //Attributes

    private String content;
    private Date moment;

    //Relationships

    private Report report;
    private ContentManager contentManager;

    //Getters

    @NotBlank
    public String getContent() {
        return content;
    }

    @Past
    public Date getMoment() {
        return moment;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Report getReport() {
        return report;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public ContentManager getContentManager() {
        return contentManager;
    }

    //Setters


    public void setContent(String content) {
        this.content = content;
    }
    public void setMoment(Date moment) {
        this.moment = moment;
    }
    public void setReport(Report report) {
        this.report = report;
    }
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }
}
