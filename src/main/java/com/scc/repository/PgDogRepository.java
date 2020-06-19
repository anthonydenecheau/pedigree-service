package com.scc.repository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scc.model.Dog;
import com.scc.model.PgDog;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class PgDogRepository implements PanacheRepository<PgDog> {

    @Inject 
    EntityManager em;
    
    public void updateDog(Long _id, Dog _dog) {
        
        System.out.println("updateDog {"+_id+"}");
        
        try {
            em.createNativeQuery(
                    "INSERT INTO PG_DATA (idDog, data) VALUES (:idDog, :data) ")
                    .setParameter(":idDog", _id)
                    .setParameter(":data", _dog.toString());
            
        } catch ( NoResultException e) {
            System.out.println("No results > updateDog {"+_id+"} " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error > updateDog {"+_id+"} " + e.getMessage());
        }
        
    }
    
}
