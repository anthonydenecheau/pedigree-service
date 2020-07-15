package com.scc.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ErrorMessage {
   
    String message;
    int code;
     
    public ErrorMessage(String message, int code) {
      super();
      this.message = message;
      this.code = code;
    }
     
    public ErrorMessage() {
     
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}