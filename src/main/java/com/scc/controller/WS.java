package com.scc.controller;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;

import com.scc.exception.NotFoundException;
import com.scc.model.Dog;
import com.scc.model.PgArbreGenealogie;
import com.scc.model.PgDog;
import com.scc.service.DogService;

@Path("/api")
//@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WS {

    private static final Logger LOG = Logger.getLogger(WS.class);

    @Inject
    DogService dogService;

    @GET
    @Path("/pedigrees/{id}")
    @Tag(ref="pedigree")
    @APIResponses(value={
        @APIResponse(
                responseCode = "200",
                description = "Successfully retrieved dog by id.",
                content = @Content(
                    schema = @Schema(implementation = Dog.class)
                )),
        @APIResponse(
                responseCode = "401",
                description = "You are not authorized to view the resource"),
        @APIResponse(
                responseCode = "403",
                description = "Accessing the resource you were trying to reach is forbidden"),
        @APIResponse(
                responseCode = "404",
                description = "The resource you were trying to reach is not found")
    })
    @Operation(
        summary = "Get dog by id",
        operationId = "getDogById")
    @SecurityRequirement(name = "jwt", scopes = {})
    @RolesAllowed("user")
    @NoCache    
    public Response getDogById(
            @Parameter(
                    name = "id",
                    description = "id that needs to be fetched",
                    schema = @Schema(type = SchemaType.INTEGER),
                    required = true
                )
            @PathParam("id") Integer id) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException{
        
        LOG.info("Appel {"+id+"}");
        
        Dog dog = dogService.findDogById(id);
        if (dog != null)
            return Response.status(Response.Status.OK).entity(dog).build();
        else
            throw new NotFoundException("Dog Not Found");
        
    }
            
            
    @GET
    @Path("/pedigrees/token/{token}")
    @Tag(ref="pedigree")
    @APIResponses(value={
        @APIResponse(
                responseCode = "200",
                description = "Successfully retrieved dog by token.",
                content = @Content(
                    schema = @Schema(implementation = Dog.class)
                )),
        @APIResponse(
                responseCode = "401",
                description = "You are not authorized to view the resource"),
        @APIResponse(
                responseCode = "403",
                description = "Accessing the resource you were trying to reach is forbidden"),
        @APIResponse(
                responseCode = "404",
                description = "The resource you were trying to reach is not found")
    })
    @Operation(
        summary = "Get dog by token",
        operationId = "getDogByToken")
    @SecurityRequirement(name = "jwt", scopes = {})
    @RolesAllowed("user")
    @NoCache
    public Response getDogByToken( 
            @Parameter(
                    name = "token",
                    description = "The token that needs to be fetched",
                    schema = @Schema(type = SchemaType.STRING),
                    required = true
                )
            @PathParam("token") String token ) throws Exception {
        
        LOG.info("Appel {"+token+"}");
        
        //Process the request
        Dog dog = dogService.findDogByToken(token);
        if (dog != null) {
            return Response.status(Response.Status.OK).entity(dog).build();
        }
        else {
            throw new NotFoundException("No dog found {"+token+"}");
        }
    }
    
    @POST
    @Transactional
    @Path("/pedigrees")
    public Response create(@Valid PgDog dog){
        
        dogService.populateDog(dog);
        return Response.status(Response.Status.CREATED).entity(dog).build();
    }

    @POST
    @Transactional
    @Path("/parents")
    public Response create(@Valid PgArbreGenealogie parents){
        
        dogService.populateParents(parents);
        return Response.status(Response.Status.CREATED).entity(parents).build();
    }

}