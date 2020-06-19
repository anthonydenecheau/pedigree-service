package com.scc.filters;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.scc.service.LoginAttemptService;

import io.vertx.core.http.HttpServerRequest;

@Provider
public class TrackingFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(TrackingFilter.class);

    @Inject 
    LoginAttemptService loginAttemptService;
    
    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    
    @Override
    public void filter(ContainerRequestContext context) {

        final String method = context.getMethod();
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();

        LOG.infof("#################################################");
        LOG.infof("Processing incoming request %s for %s [%s].", method, path, address);
        LOG.infof("#################################################");
        if (loginAttemptService.isBlocked(address)) 
            LOG.infof("Error : Too many attempts");
        
        // [TODO] Implementer la règle
        if (0>1)
            loginAttemptService.loginFailed(address);
        
    }
    

}
