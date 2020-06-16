package com.scc.exception;

public class ErrorResponse {
   
    String errorMessage;
    String errorCode;
     
    public ErrorResponse(String errorMessage, String errorCode) {
      super();
      this.errorMessage = errorMessage;
      this.errorCode = errorCode;
    }
     
    public ErrorResponse() {
     
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}