package com.scc.service;

import javax.enterprise.context.ApplicationScoped;

import com.scc.model.Breeder;

@ApplicationScoped
public class BreederService extends AbstractGenericService<Breeder> {

    public BreederService() {
        super();
        this.setType(Breeder.class);
    }

    public Breeder findBreederByIdDog(int id) {

        Breeder _a = new Breeder();

        try {

        } catch (Exception e) {
        } finally {
        }
        return _a;
    }
}
