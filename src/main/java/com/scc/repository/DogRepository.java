package com.scc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scc.model.Dog;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class DogRepository implements PanacheRepository<Dog> {

    @Inject 
    EntityManager em;
      
    public List<Dog> findByToken(String token) {
        
        System.out.println("DogRepository findByToken Y {"+token+"}");
        List<Dog> dogs = new ArrayList<Dog>();
        
        try {
            @SuppressWarnings("unchecked")
            List<String> _dogs =  (List<String>) em.createNativeQuery(
                    "SELECT jsonb_pretty(data) " +
                    "FROM PG_DATA " +
                    "WHERE data->'tokens' @> '[{\"number\": \""+token+"\"}]'")
                    //"WHERE data->'tokens' @> jsonb_build_object('number', :token)")
                    //.setParameter("token", token)
                    //.getSingleResult();
                    .getResultList();
            
            System.out.println("chien trouvé {"+_dogs.size()+"}");
            if (_dogs.size()>0) {
                ObjectMapper mapper = new ObjectMapper();
                for (String _dog : _dogs)
                    dogs.add(mapper.readValue(_dog, Dog.class));
                ;
            } 
            //else
            //    throw new Exception("No dog found {"+token+"}");
        }catch (Exception e){
            System.out.println("Error > findByToken {"+token+"}");
        }        
        return dogs;
    }
    
    public Dog findDog(Integer idDog) {
        
        System.out.println("findDog {"+idDog+"}");
        Dog _dog = null;
        try {
            String _d =  (String) em.createNativeQuery(
                    "SELECT jsonb_pretty(data) " +
                    "FROM PG_DATA " +
                    "WHERE idDog = :idDog")
                    .setParameter("idDog", idDog)
                    .getSingleResult();
            
            if (_d != null|| "".equals(_d)) {
                ObjectMapper mapper = new ObjectMapper();
                _dog = mapper.readValue(_d, Dog.class);
            }
            
        } catch ( NoResultException e) {
            System.out.println("No results > findDog {"+idDog+"} " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error > findDog {"+idDog+"} " + e.getMessage());
        }
        return _dog;
        
    }

}
