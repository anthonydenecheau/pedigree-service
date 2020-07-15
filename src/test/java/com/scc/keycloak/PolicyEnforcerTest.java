package com.scc.keycloak;

import org.junit.jupiter.api.Test;
import org.keycloak.representations.AccessTokenResponse;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.DisabledOnNativeImage;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@Testcontainers
@QuarkusTest
@DisabledOnNativeImage
@QuarkusTestResource(PostgreSQLResource.class)
@QuarkusTestResource(KeycloakResource.class)
public class PolicyEnforcerTest {
        
    //private static final String KEYCLOAK_SERVER_URL = System.getProperty("keycloak.url", "http://"+KeycloakServer.keycloak.getContainerIpAddress()+":"+KeycloakServer.keycloak.getFirstMappedPort()+"/auth");    
    private static final String KEYCLOAK_SERVER_URL = System.getProperty("quarkus.oidc.auth-server-url", "http://localhost:8180/auth/realms/quarkus");

    @Test
    public void testAccessUserResource() {
        System.out.println("############testAccessUserResource#############");
        RestAssured.given().auth().oauth2(getAccessToken("alice"))
                .when().get("/api/v1/pedigrees/token/JVP685")
                .then()
                .statusCode(200);
    }

    private String getAccessToken(String userName) {
        System.out.println("############"+KEYCLOAK_SERVER_URL+"/protocol/openid-connect/token"+"#############");
        return RestAssured
                .given()
                .param("grant_type", "password")
                .param("username", userName)
                .param("password", userName)
                .param("client_id", "backend-service")
                .param("client_secret", "secret")
                .when()
                .post(KEYCLOAK_SERVER_URL+ "/protocol/openid-connect/token")
                .as(AccessTokenResponse.class).getToken();
    }

    /*
    @Test
    public void testHealthCheck() {
        given()
                .when()
                .get("/public/health/live")
                .then()
                .statusCode(200)
                .body("checks.name", everyItem(anyOf(
                        is("WS is alive !"),
                        is("Health check with data"))))
       ;
    }
    */

    /*
    @Test
    public void testAccessAdminResource() {
        RestAssured.given().auth().oauth2(getAccessToken("alice"))
                .when().get("/api/admin")
                .then()
                .statusCode(403);
        RestAssured.given().auth().oauth2(getAccessToken("jdoe"))
                .when().get("/api/admin")
                .then()
                .statusCode(403);
        RestAssured.given().auth().oauth2(getAccessToken("admin"))
                .when().get("/api/admin")
                .then()
                .statusCode(200);
    }
    */
    /*
    @Test
    public void testPublicResource() {
        RestAssured.given()
                .when().get("/public")
                .then()
                .statusCode(200);
    }
    */
    
}
