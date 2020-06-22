package com.scc.filters;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.scc.service.LoginAttemptService;

import io.vertx.core.http.HttpServerRequest;

//@Priority(1)
@Provider
public class TrackingFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(TrackingFilter.class);

    public static final String AUTHENTICATION_KEY = "X-SCC-authentification";
    
    @ConfigProperty(name = "access.authentication.key")    
    String KEY;
    
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
        
        LOG.infof(">>>>> " + info.getAbsolutePath().getPath());
                
        if ( path.indexOf("public/admin") > 0 ) {
            String authCredentials = request.getHeader(AUTHENTICATION_KEY);
            if (!authenticate(authCredentials)) {
                String msg = String.format("Erreur d'authentification, clef fournie: %s", authCredentials);
                LOG.errorf(msg);
                
                if (loginAttemptService.isBlocked(address)) 
                    LOG.infof("Error : Too many attempts");
                
                // [TODO] Implementer la règle
                loginAttemptService.loginFailed(address);

                CacheControl cc = new CacheControl();
                cc.setNoStore(true);
                Response response = Response.status(Response.Status.FORBIDDEN)
                                            .cacheControl(cc)
                                            .entity(msg)
                                            .build();                    
                
                context.abortWith(response);
            }
        }
        
    }
    
    private boolean authenticate(String authCredentials) {
        
        Boolean ok = false;
        
        if (null == authCredentials)
            return ok;

        // la clé transmise est-elle reconnue ?
        if (KEY.equals(authCredentials))
           ok = true;

        return ok;
    }

}
