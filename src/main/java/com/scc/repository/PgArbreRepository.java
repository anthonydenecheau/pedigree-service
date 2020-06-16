package com.scc.repository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.scc.model.PgArbreGenealogie;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@Singleton
public class PgArbreRepository implements PanacheRepository<PgArbreGenealogie> {

    @Inject 
    EntityManager em;
      
    public PgArbreGenealogie findParents(Integer idDog) {
        System.out.println("findParents {"+idDog+"}");
        PgArbreGenealogie _pg = null;
        try{
            TypedQuery<PgArbreGenealogie> typedQuery = em.createNamedQuery("findParents", PgArbreGenealogie.class);
            typedQuery.setParameter("idDog", idDog);
            _pg = typedQuery.getSingleResult();
        } catch ( NoResultException e) {
            System.out.println("No results > findParents {"+idDog+"} " + e.getMessage());       
        } catch (Exception e){
            System.out.println("Error > findParents {"+idDog+"} " + e.getMessage());
        }
        return _pg;
    }
}
