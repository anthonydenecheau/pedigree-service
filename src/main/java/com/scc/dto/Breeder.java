package com.scc.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Breeder extends Hidden {

    @Schema(required = true, description = "civility")
    private String civility;
    @Schema(required = true, description = "lastName")
    private String lastName;

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

    @Override
    public String toString() {
        return "Breeder [civility=" + civility + ", lastName=" + lastName + ", hidden=" + hidden + "]";
    }

}
