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
      return Response.status(Response.Status.NOT_FOUND)
              .entity(ex.getMessage())
              .type(MediaType.APPLICATION_JSON)
              .build();
    }
}