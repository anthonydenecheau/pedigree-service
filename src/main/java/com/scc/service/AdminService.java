package com.scc.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.scc.model.PgArbreGenealogie;
import com.scc.model.PgDog;
import com.scc.repository.PgArbreRepository;
import com.scc.repository.PgDogRepository;

@ApplicationScoped
public class AdminService {
    
    @Inject 
    PgArbreRepository pgArbreRepository;
    
    @Inject 
    PgDogRepository pgDogRepository;
    
    @Transactional
    public void populateDog(PgDog _dog) {
        PgDog _d = pgDogRepository.findById(_dog.getIdDog());
        if (_d != null)
            pgDogRepository.update("data = ?1 where idDog = ?2", _dog.getData(), _dog.getIdDog());
        else
            pgDogRepository.persistAndFlush(_dog);
    }

    @Transactional
    public void populateParents(PgArbreGenealogie _parents) {
        PgArbreGenealogie _parent = pgArbreRepository.findById(_parents.getIdDog());
        if (_parent != null)
            pgArbreRepository.update("idFather = ?1, idMother = ?2 where idDog = ?3", _parents.getIdFather(), _parents.getIdMother(), _parents.getIdDog());
        else
            pgArbreRepository.persistAndFlush(_parents);
    }
}
