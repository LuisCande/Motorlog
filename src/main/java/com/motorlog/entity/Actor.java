
package com.motorlog.entity;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.motorlog.security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

    //Attributes

    private String						name;
    private String						email;
    private String						identifier;
    private String						phone;
    private String						country;
    private String						city;
    private String						postalCode;
    private String						address;

    //Relationships

    private UserAccount					userAccount;


    //Getter

    @NotBlank
    public String getName() {
        return this.name;
    }

    @Email
    public String getEmail() {
        return this.email;
    }

    @NotBlank
    public String getIdentifier() {
        return this.identifier;
    }

    @NotBlank
    public String getPhone() {
        return this.phone;
    }

    @NotBlank
    public String getCountry() {
        return this.country;
    }

    @NotBlank
    public String getCity() {
        return this.city;
    }

    @NotBlank
    public String getPostalCode() {
        return this.postalCode;
    }

    @NotBlank
    public String getAddress() {
        return this.address;
    }

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    //Setters

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode= postalCode;
    }

    public void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
    }



}
