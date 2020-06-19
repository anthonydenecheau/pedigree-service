package com.scc.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.scc.annotation.Role;
import com.scc.model.Dna;
import com.scc.model.Dog;
import com.scc.model.Health;
import com.scc.model.Hidden;
import com.scc.model.PgArbreGenealogie;
import com.scc.repository.PgArbreRepository;
import com.scc.repository.DogRepository;

import io.quarkus.security.identity.SecurityIdentity;

@ApplicationScoped
public class DogService extends AbstractGenericService<Dog> {

    @ConfigProperty(name = "level.generation")
    int level;

    @Inject 
    PgArbreRepository pgArbreRepository;
    
    @Inject 
    DogRepository dogRepository;

    @Inject
    SecurityIdentity identity;
    
    public Dog findDogById(Integer id) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException{
        return null;
    }

    public Dog findDogByToken(String token) throws Exception {

        System.out.println("Start readDog level " +level);
        List<Dog> _dogs= new ArrayList<Dog>();
        Dog _dog = new Dog();
        try {

             _dogs = dogRepository.findByToken(token);
             PgArbreGenealogie _parents = null;
             
             if (_dogs.size()>0 && _dogs != null) {
                 _dog = handleRole(_dogs.get(0));
                 _parents = pgArbreRepository.findParents(_dog.getId());
                 if (_parents != null)
                     if (level>0)
                         findFamilyTree(_dog, _parents.idFather, _parents.idMother, 1);
             } else {
                 return null;
             }

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            System.out.println("End readDog");
        }
        return _dog;
    }

    /**
     * Recherche les ascendants dans la limite de generation définie
     * 
     * @param chien
     * @param generation
     *            nb de génération à retourner
     * @throws Exception
     */
    private void findFamilyTree(Dog _dog, Integer _idFather, Integer _idMother, int _level){
        Dog _f = null;
        Dog _m = null;
        
        //System.out.println("findFamilyTree level " + _level);
        
        if (_idFather != null) {
            System.out.println("findFamilyTree level " + _level + "Father ["+_idFather+"]");
            _f = handleRole(dogRepository.findDog(_idFather));
        }
        if (_idMother != null) {
            System.out.println("findFamilyTree level " + _level + "Mother ["+_idMother+"]");
            _m = handleRole(dogRepository.findDog(_idMother));
        }

        if (_m != null) {
            _dog.setMother(_m);
            if (_level < level) {
                PgArbreGenealogie _parents = pgArbreRepository.findParents(_m.getId());
                if (_parents!=null)
                    findFamilyTree(_m, _parents.idFather, _parents.idMother, _level + 1);
            }
        } 
        if (_f != null) {
            _dog.setFather(_f);
            if (_level < level) {
                PgArbreGenealogie _parents = pgArbreRepository.findParents(_f.getId());
                if (_parents!=null)
                    findFamilyTree(_f, _parents.idFather, _parents.idMother, _level + 1);
            }
        }

    }    

    public Dog handleRole (Dog _d)  {
        
        if ( _d == null)
            return null;
        
        for (Field field : _d.getClass().getDeclaredFields()) {
            
            // On souhaite accéder à des propriétés de type private
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            
            // Gestion de la visibilité en fonction du rôle
            for (Annotation annotation : field.getAnnotations()) {
                
                try {
                    if (annotation.annotationType().equals(Role.class)) {
                        String _role = field.getAnnotation(Role.class).name();
                        if ("".equals(_role) || _role == null) {
                            continue;
                        }
                        if (!identity.hasRole(_role)) {
                            // l'utilisateur n'a pas le role necessaire pour afficher la propriété
                            if (field.getType().isAssignableFrom(Dna.class)) {
                                if (field.get(_d) != null) {
                                    final Hidden oldValue = (Hidden) field.get(_d);
                                    field.set(_d, oldValue.withHidden(true));
                                }
                            }
                            if (field.getGenericType() instanceof ParameterizedType) {
                                ParameterizedType pType = (ParameterizedType)field.getGenericType();
                                //System.out.print("Raw type: " + pType.getRawType() + " - ");
                                //System.out.println("Type args: " + pType.getActualTypeArguments()[0]);
                                Type actualType = pType.getActualTypeArguments()[0];
                                if (actualType.getTypeName().equals(Health.class.getTypeName())) {
                                    if (field.get(_d) != null) {
                                        List<Hidden> oldValue = (List<Hidden>) field.get(_d);
                                        oldValue.forEach(f -> f.setHidden(true));
                                        field.set(_d, oldValue );
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    
                }
            }
            field.setAccessible(accessible);
        }        
        return _d;
    }

}
