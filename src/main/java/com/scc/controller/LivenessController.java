package com.scc.controller;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@ApplicationScoped
@Liveness
public class LivenessController implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("WS Health Check").up().build();
    }

}
