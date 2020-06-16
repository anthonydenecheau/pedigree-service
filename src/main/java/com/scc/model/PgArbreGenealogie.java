package com.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="PG_ARBRE_GENEALOGIE")
@NamedQuery(name = "findParents",
    query = "SELECT f FROM PgArbreGenealogie f where idDog = :idDog")
    //,hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class PgArbreGenealogie extends PanacheEntityBase {

    @Id
    @Column( name = "id_chien" )
    public Integer idDog;
    @Column( name = "id_pere" )
    public Integer idFather;
    @Column( name = "id_mere" )
    public Integer idMother;
    
    
}
