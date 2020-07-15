package com.scc.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import com.scc.dto.PgArbreGenealogie;
import com.scc.dto.PgDog;
import com.scc.service.AdminService;

@Path("/public/admin")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

    private static final Logger LOG = Logger.getLogger(AdminResource.class);
    
    @Inject
    AdminService adminService;
    
    @POST
    @Transactional
    @Path("/pedigrees")
    @Tag(ref="admin")
    public Response create(@Valid PgDog dog) throws Exception {
        LOG.info("populateDog " + dog.getIdDog());
        adminService.populateDog(dog);
        LOG.info(" > DONE");
        return Response.status(Response.Status.CREATED).entity(dog).build();
    }

    @POST
    @Transactional
    @Path("/parents")
    @Tag(ref="admin")    
    public Response create(@Valid PgArbreGenealogie parents) throws Exception {
        LOG.info("populateParents " + parents.getIdDog());
        adminService.populateParents(parents);
        return Response.status(Response.Status.CREATED).entity(parents).build();
    }
}
