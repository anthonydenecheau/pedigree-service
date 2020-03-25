package com.scc.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dna extends Hidden {

    @Schema(required = false, description = "Dna code")
    private String code;
    @Schema(required = false, description = "Dna result")
    private String libelle;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    @Override
    public String toString() {
        return "Dna [code=" + code + ", libelle=" + libelle + ", hidden=" + hidden + "]";
    }

}
