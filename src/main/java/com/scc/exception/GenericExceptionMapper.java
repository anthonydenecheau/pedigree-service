package com.scc.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        errorMessage.setMessage(ex.getMessage());


        return Response.status(errorMessage.getCode())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();   
    }
}