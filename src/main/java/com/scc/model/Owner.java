package com.scc.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner extends Hidden {

    @Schema(required = true, description = "civility")
    private String civility;
    @Schema(required = true, description = "lastName")
    private String lastName;
    @Schema(required = true, description = "firstName")
    private String firstName;

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Owner [civility=" + civility + ", lastName=" + lastName+ ", firstName=" + firstName + ", hidden=" + hidden + "]";
    }

}
