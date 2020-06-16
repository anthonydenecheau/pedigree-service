package com.scc.repository;

import javax.inject.Singleton;

import com.scc.model.PgDog;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class PgDogRepository implements PanacheRepository<PgDog> {

}
