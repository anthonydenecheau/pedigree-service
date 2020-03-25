package com.scc.service;

import javax.enterprise.context.ApplicationScoped;

import com.scc.model.Breed;

@ApplicationScoped
public class BreedService extends AbstractGenericService<Breed> {

    public BreedService() {
        super();
        this.setType(Breed.class);
    }

    public Breed findBreedByIdDog(int id) {

        Breed _a = new Breed();

        try {

        } catch (Exception e) {
        } finally {
        }
        return _a;
    }
}
