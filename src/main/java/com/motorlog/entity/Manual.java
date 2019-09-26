package com.motorlog.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Manual extends DomainEntity {

    //Attributes

    //TODO Tipo PDF - importar una libreria maybe? (String para que no salga rojo por el momento)
    private byte[] pdfFile;
    private Collection<Date> revisionDates;

    //Getters

    public byte[] getPdfFile() {
        return this.pdfFile;
    }

    @NotNull
    @NotEmpty
    @ElementCollection
    public Collection<Date> getRevisionDates() {
        return this.revisionDates;
    }

    //Setters

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public void setRevisionDates(Collection<Date> revisionDates) {
        this.revisionDates = revisionDates;
    }
}
