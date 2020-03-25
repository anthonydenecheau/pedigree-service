package com.scc.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    
    @Override
    public Response toResponse(NotFoundException ex) {
      ErrorResponse response = new ErrorResponse(ex.getMessage(), "404");
      return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }
}