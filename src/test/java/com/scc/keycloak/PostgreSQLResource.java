package com.scc.keycloak;

import java.util.Collections;
import java.util.Map;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class PostgreSQLResource implements QuarkusTestResourceLifecycleManager {

    private static PostgreSQLContainer db;

    @SuppressWarnings("resource")
    @Override
    public Map<String, String> start() {
        System.out.println("########START POSTGRESQL########");
        db = new PostgreSQLContainer<>("postgres:11-alpine")
                .withDatabaseName("quarkus_test")
                .withUsername("quarkus_test")
                .withPassword("quarkus_test")
                .withExposedPorts(5432)
                // Below should not be used - Function is deprecated and for simplicity of test , You should override your properties at runtime
                .withCreateContainerCmdModifier(cmd -> {
                    cmd
                            .withHostName("localhost")
                            .withPortBindings(new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)));
                });
        db.start();
        return Collections.singletonMap("db.bootstrap.ip", db.getContainerIpAddress());
    }

    @Override
    public void stop() {
        System.out.println("########STOP POSTGRESQL########");
        db.stop();
    }
}
