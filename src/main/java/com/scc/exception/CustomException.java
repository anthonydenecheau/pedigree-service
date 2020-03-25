package com.scc.exception;

import java.io.Serializable;

public class CustomException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int code;

    public CustomException() {
        super();
    }
    public CustomException(String msg)   {
        super(msg);
    }
    public CustomException(String msg, Exception e)  {
        super(msg, e);
    }
    public CustomException (int code, String msg) {
        super(msg);
        this.code = code;
    }
    
}
