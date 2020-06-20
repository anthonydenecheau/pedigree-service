package com.scc.repository;

import java.math.BigDecimal;

import javax.inject.Inject;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scc.model.Dog;
import com.scc.model.PgDog;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class PgDogRepository implements PanacheRepository<PgDog> {

    private static final Logger LOG = Logger.getLogger(PgDogRepository.class);

    @Inject 
    EntityManager em;
    
    public int findByIdDog(Long _id) {
        
        System.out.println("PgDogRepository findByIdDog Y {"+_id+"}");
        BigDecimal _d = BigDecimal.ZERO;
        
        try {
            _d =  (BigDecimal) em.createNativeQuery(
                    "SELECT idDog " +
                    "FROM PG_DATA " +
                    "WHERE idDog = ? ")
                    .setParameter(1, _id)
                    .getSingleResult();
            
            if (_d.compareTo(BigDecimal.ZERO) == 0)
                return -1;
            
        }catch (Exception e){
            LOG.error("Error > findByIdDog {"+_id+"}"  + e.getMessage());
            return -1;
        }finally {
        }
        return 1;
    }
    
    public void insert (PgDog _dog) {

        System.out.println("insert {"+_dog.getIdDog()+"}");
        try {
            em.createNativeQuery(
                    "INSERT INTO PG_DATA (idDog, data) VALUES (?, ?) ")
                    .setParameter(1, _dog.getIdDog())
                    .setParameter(2, _dog.getData())
                    .executeUpdate();
            em.flush();
        } catch ( NoResultException e) {
            LOG.error("No results > insert {"+_dog.getIdDog()+"} " + e.getMessage());
        } catch (Exception e){
            LOG.error("Error > insert {"+_dog.getIdDog()+"} " + e.getMessage());
        }

    }
    
    public void insert(Long _id, Dog _dog) {
        
        System.out.println("insert {"+_id+"}");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            em.createNativeQuery(
                    "INSERT INTO PG_DATA (idDog, data) VALUES (?, to_jsonb(?)) ")
                    .setParameter(1, _id)
                    .setParameter(2, mapper.writeValueAsString(_dog))
                    .executeUpdate();
            em.flush();
        } catch ( NoResultException e) {
            LOG.error("No results > insert {"+_id+"} " + e.getMessage());
        } catch (Exception e){
            LOG.error("Error > insert {"+_id+"} " + e.getMessage());
        }
        
    }
    
}
