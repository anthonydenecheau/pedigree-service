package com.scc.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.scc.model.Health;

@ApplicationScoped
public class HealthService extends AbstractGenericService<Health> {

    public HealthService() {
        super();
        this.setType(Health.class);
    }

    public List<Health> findHealthByIdDog(int id) {

        List<Health> _l = new ArrayList<Health>();

        try {

        } catch (Exception e) {
        } finally {
        }
        return _l;
    }
}
