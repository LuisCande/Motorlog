/*
 * Authority.java
 *
 * Copyright (C) 2019 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package com.motorlog.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority {

    // Constructors -----------------------------------------------------------

    private static final long serialVersionUID = 1L;


    public Authority() {
        super();
    }

    public Authority(String authority) {
        super();
        this.authority = authority;
    }


    // Values -----------------------------------------------------------------

    public static final String ADMIN = "ADMIN";
    public static final String CONTENTMANAGER = "CONTENTMANAGER";
    public static final String GARAGE = "GARAGE";

    // Attributes -------------------------------------------------------------

    private String authority;


    @NotBlank
    @Pattern(regexp = "^" + Authority.ADMIN + "|" + Authority.CONTENTMANAGER + "|" + Authority.GARAGE + "$")
    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    public static Collection<Authority> listAuthorities() {
        Collection<Authority> result;
        Authority authority;

        result = new ArrayList<>();

        authority = new Authority();
        authority.setAuthority(Authority.ADMIN);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.CONTENTMANAGER);
        result.add(authority);

        authority = new Authority();
        authority.setAuthority(Authority.GARAGE);
        result.add(authority);

        return result;
    }

    // Object interface -------------------------------------------------------

    @Override
    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        boolean result;

        if (this == other)
            result = true;
        else if (other == null)
            result = false;
        else if (!this.getClass().isInstance(other))
            result = false;
        else
            result = (this.getAuthority().equals(((Authority) other).getAuthority()));

        return result;
    }

    @Override
    public String toString() {
        return this.authority;
    }

}
