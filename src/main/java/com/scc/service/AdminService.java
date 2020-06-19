package com.scc.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.scc.controller.AdminController;
import com.scc.model.Dog;
import com.scc.model.PgArbreGenealogie;
import com.scc.model.PgDog;
import com.scc.repository.DogRepository;
import com.scc.repository.PgArbreRepository;
import com.scc.repository.PgDogRepository;

@ApplicationScoped
public class AdminService {
    
    private static final Logger LOG = Logger.getLogger(AdminService.class);
    
    @Inject 
    DogRepository dogRepository;
    
    @Inject 
    PgArbreRepository pgArbreRepository;
    
    @Inject 
    PgDogRepository pgDogRepository;
    
    @Transactional
    public void populateDog(PgDog _dog) {
        Dog _d = dogRepository.findDog(_dog.getIdDog().intValue());
        //PgDog _d = pgDogRepository.findById(_dog.getIdDog());
        if (_d != null) {
            LOG.info(" > UPDATE");
            pgDogRepository.update("data = ?1 where idDog = ?2", _dog.getData(), _dog.getIdDog());
        } else {
            LOG.info(" > CREATE");
            pgDogRepository.updateDog(_dog.getIdDog(), _dog.getData());
            //pgDogRepository.persistAndFlush(_dog);
        }
    }

    @Transactional
    public void populateParents(PgArbreGenealogie _parents) {
        PgArbreGenealogie _p = pgArbreRepository.findById(_parents.getIdDog());
        if (_p != null)
            pgArbreRepository.update("idFather = ?1, idMother = ?2 where idDog = ?3", _parents.getIdFather(), _parents.getIdMother(), _parents.getIdDog());
        else
            pgArbreRepository.persistAndFlush(_parents);
    }
}
