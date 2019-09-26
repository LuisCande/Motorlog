
package com.motorlog.entity;

import java.util.Collection;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

    //Attributes

    private String				systemName;
    private String				systemBanner;
    private String              systemLogo;
    private String				welcomeEN;
    private String				welcomeES;
    private Double				vat;
    private Map<Integer, Double> pricePerMonth;
    private Collection<String> itemsForMoto;
    private Collection<String> itemsForCar;
    private String             termsAndConditions;


    //Getters

    @NotBlank
    public String getSystemName() {
        return this.systemName;
    }

    @NotBlank
    @URL
    public String getSystemBanner() {
        return this.systemBanner;
    }

    @NotBlank
    @URL
    public String getSystemLogo() {
        return this.systemLogo;
    }

    @NotNull
    public String getWelcomeEN() {
        return this.welcomeEN;
    }

    @NotNull
    public String getWelcomeES() {
        return this.welcomeES;
    }

    @Min(0)
    public Double getVat() {
        return this.vat;
    }

    @ElementCollection
    @NotEmpty
    @NotNull
    public Map<Integer, Double> getPricePerMonth() {
        return this.pricePerMonth;
    }

    @ElementCollection
    @NotEmpty
    @NotNull
    public Collection<String> getItemsForMoto() {
        return this.itemsForMoto;
    }

    @ElementCollection
    @NotEmpty
    @NotNull
    public Collection<String> getItemsForCar() {
        return this.itemsForCar;
    }

    @NotNull
    public String getTermsAndConditions() {
        return this.termsAndConditions;
    }

    //Setters

    public void setSystemName(final String systemName) {
        this.systemName = systemName;
    }

    public void setSystemBanner(final String systemBanner) {
        this.systemBanner = systemBanner;
    }

    public void setSystemLogo(final String systemLogo) {
        this.systemLogo = systemLogo;
    }

    public void setWelcomeEN(final String welcomeEN) {
        this.welcomeEN = welcomeEN;
    }

    public void setWelcomeES(final String welcomeES) {
        this.welcomeES = welcomeES;
    }

    public void setVat(final Double vat) {
        this.vat = vat;
    }

    public void setPricePerMonth(final Map<Integer, Double> pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public void setItemsForMoto(final Collection<String> itemsForMoto) {this.itemsForMoto = itemsForMoto;}

    public void setItemsForCar(final Collection<String> itemsForCar) {this.itemsForCar = itemsForCar;}

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }
}
