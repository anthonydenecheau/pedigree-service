package com.scc.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pedigree extends Hidden {

    @Schema(required = true, description = "country")
    private String country;
    @Schema(required = true, description = "type")
    private String type;
    @Schema(required = true, description = "number")
    private String number;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Pedigree [country=" + country + ", type=" + type + ", number=" + number + ", hidden=" + hidden + "]";
    }

}
