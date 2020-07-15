package com.scc.exception;

import javax.annotation.Priority;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Priorities;

@Provider
@Priority(Priorities.USER + 1)
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    
    @Override
    public Response toResponse(NotFoundException ex) {
        
        ErrorMessageNF errorMessage = new ErrorMessageNF();
        errorMessage.code = Response.Status.NOT_FOUND.getStatusCode();
        errorMessage.message = ex.getMessage();
        
        return Response.status(errorMessage.code)
              .entity(errorMessage)
              .type(MediaType.APPLICATION_JSON)
              .build();
    }
    
    public static class ErrorMessageNF {
        public  String message;
        public  int code;
    }
    
}