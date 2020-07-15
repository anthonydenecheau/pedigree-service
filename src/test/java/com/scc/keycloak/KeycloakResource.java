package com.scc.keycloak;

import java.util.Collections;
import java.util.Map;

import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class KeycloakResource  implements QuarkusTestResourceLifecycleManager  {

    private static GenericContainer keycloak;

    @SuppressWarnings("resource")
    @Override
    public Map<String, String> start() {
        System.out.println("########START KEYCLOAK########");
        keycloak = new FixedHostPortGenericContainer("quay.io/keycloak/keycloak:7.0.1")
                .withFixedExposedPort(8180, 8080)
                .withEnv("KEYCLOAK_USER", "admin")
                .withEnv("KEYCLOAK_PASSWORD", "admin")
                .withEnv("KEYCLOAK_IMPORT", "/tmp/quarkus-realm.json")
                .withClasspathResourceMapping("quarkus-realm.json", "/tmp/quarkus-realm.json", BindMode.READ_ONLY)
                .waitingFor(Wait.forHttp("/auth"));
        keycloak.start();
        return Collections.singletonMap("keycloak.bootstrap.ip", keycloak.getContainerIpAddress());
    }

    @Override
    public void stop() {
        System.out.println("########STOP KEYCLOAK########");
        keycloak.stop();
    }
}
