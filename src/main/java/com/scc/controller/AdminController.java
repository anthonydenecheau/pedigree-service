package com.scc.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.scc.model.PgArbreGenealogie;
import com.scc.model.PgDog;
import com.scc.service.AdminService;

@Path("/public/admin")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    @Inject
    AdminService adminService;
    
    @POST
    @Transactional
    @Path("/pedigrees")
    public Response create(@Valid PgDog dog){
        
        adminService.populateDog(dog);
        return Response.status(Response.Status.CREATED).entity(dog).build();
    }

    @POST
    @Transactional
    @Path("/parents")
    public Response create(@Valid PgArbreGenealogie parents){
        
        adminService.populateParents(parents);
        return Response.status(Response.Status.CREATED).entity(parents).build();
    }
}
