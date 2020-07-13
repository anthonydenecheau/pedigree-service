package com.scc.repository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import com.scc.dto.PgArbreGenealogie;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class PgArbreRepository implements PanacheRepository<PgArbreGenealogie> {

    private static final Logger LOG = Logger.getLogger(PgArbreRepository.class);
    
    @Inject 
    EntityManager em;
      
    public PgArbreGenealogie findParents(int idDog) {
        System.out.println("findParents {"+idDog+"}");
        PgArbreGenealogie _pg = null;
        try{
            TypedQuery<PgArbreGenealogie> typedQuery = em.createNamedQuery("findParents", PgArbreGenealogie.class);
            typedQuery.setParameter("idDog", Long.valueOf(idDog));
            _pg = typedQuery.getSingleResult();
        } catch ( NoResultException e) {
            LOG.error("No results > findParents {"+idDog+"} " + e.getMessage());       
        } catch (Exception e){
            LOG.error("Error > findParents {"+idDog+"} " + e.getMessage());
        }
        return _pg;
    }
}
