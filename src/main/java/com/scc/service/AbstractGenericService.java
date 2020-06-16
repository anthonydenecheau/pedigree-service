package com.scc.service;

public abstract class AbstractGenericService<T> {

    protected Class<T> type;

    protected Class<T> getType() {
        return type;
    }

    protected void setType(Class<T> type) {
        this.type = type;
    }
    
}
