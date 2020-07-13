package com.scc.dto;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scc.utils.Hidable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hidden implements Hidable {

    @Schema(hidden = true )
    @JsonIgnore
    protected boolean hidden;

    @Override
    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
    
    public Hidden withHidden(boolean hidden) {
        this.setHidden(hidden);
        return this;
    }
        
}
