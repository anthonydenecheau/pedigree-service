package com.scc.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Breed extends Hidden {

    @Schema(required = true, description = "Breed id")
    private int idRace;
    @Schema(required = true, description = "Breed fci number")
    private String codeFci;

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public String getCodeFci() {
        return codeFci;
    }

    public void setcodeFci(String codeFci) {
        this.codeFci = codeFci;
    }

    @Override
    public String toString() {
        return "Breed [idRace=" + idRace + ", codeFci=" + codeFci + ", hidden=" + hidden + "]";
    }

}
