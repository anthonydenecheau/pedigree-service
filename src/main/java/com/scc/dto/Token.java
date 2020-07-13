package com.scc.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token extends Hidden {

    @Schema(required = true, description = "type")
    private String type;
    @Schema(required = true, description = "number")
    private String number;

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
        return "Token [type=" + type + ", number=" + number + ", hidden=" + hidden + "]";
    }

}
