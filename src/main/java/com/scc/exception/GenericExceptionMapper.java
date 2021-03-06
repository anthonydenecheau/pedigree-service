package com.scc.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Override
    public Response toResponse(Throwable arg0) {
      ErrorResponse response = new ErrorResponse(arg0.getMessage(), "500");
      arg0.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
    }
}