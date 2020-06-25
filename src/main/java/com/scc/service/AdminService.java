package com.scc.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.scc.dto.PgArbreGenealogie;
import com.scc.dto.PgDog;
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
        int _id = pgDogRepository.findByIdDog(_dog.getIdDog());
        if (_id > 0) {
            LOG.info(" > UPDATE");
            pgDogRepository.update("data = ?1 where idDog = ?2", _dog.getData(), _dog.getIdDog());
        } else {
            LOG.info(" > CREATE");
            //pgDogRepository.insert(_dog.getIdDog(), _dog.getData());
            //pgDogRepository.insert(_dog);
            pgDogRepository.persistAndFlush(_dog);
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
