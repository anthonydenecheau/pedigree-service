package com.scc.documentation;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(title = "WS API",
        description = "This API allows retreive pedigree on a dog",
        version = "1.0",
        contact = @Contact(name = "Centrale Canine", url = "https://www.centrale-canine.fr/")),
    servers = {
        @Server(url = "https://ws-pedigree-service.elhadir.com")
    },
    externalDocs = @ExternalDocumentation(url = "https://github.com/anthonydenecheau/pedigree-service/wiki", description = "Wiki"),
    tags = {
        @Tag(name = "dog", description = "Pedigree")
    }
)
public class WSApplication extends Application {

}