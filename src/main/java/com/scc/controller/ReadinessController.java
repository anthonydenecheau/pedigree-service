package com.scc.controller;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
public class ReadinessController implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("WS Health Check").up().build();
    }

}
