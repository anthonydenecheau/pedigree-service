package com.scc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scc.model.Dog;
import com.scc.model.MyJsonType;
import com.scc.model.PgDog;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class PgDogRepository implements PanacheRepository<PgDog> {

    @Inject 
    EntityManager em;
    
    public int findByIdDog(int _id) {
        
        System.out.println("PgDogRepository findByIdDog Y {"+_id+"}");
        try {
            @SuppressWarnings("unchecked")
            String _d =  (String) em.createNativeQuery(
                    "SELECT idDog " +
                    "FROM PG_DATA " +
                    "WHERE idDog = ? ")
                    .setParameter(1, _id)
                    .getSingleResult();
            
            if (_d == null || "".equals(_d))
                return 0;
            
        }catch (Exception e){
            System.out.println("Error > findByIdDog {"+_id+"}");
        }finally {
        }
        return 1;
    }
    
    public void insert(Long _id, Dog _dog) {
        
        System.out.println("insert {"+_id+"}");
        try {
            ObjectMapper mapper = new ObjectMapper();
            em.createNativeQuery(
                    "INSERT INTO PG_DATA (idDog, data) VALUES (?, to_jsonb(?)) ")
                    .setParameter(1, _id)
                    .setParameter(2, mapper.writeValueAsString(_dog))
                    .executeUpdate();
            em.flush();
        } catch ( NoResultException e) {
            System.out.println("No results > insert {"+_id+"} " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error > insert {"+_id+"} " + e.getMessage());
        }
        
    }
    
}
