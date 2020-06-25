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
    public Long idDog;
    @Column( name = "id_pere" )
    public Integer idFather;
    @Column( name = "id_mere" )
    public Integer idMother;
    
    public Long getIdDog() {
        return idDog;
    }
    public void setIdDog(Long idDog) {
        this.idDog = idDog;
    }
    public Integer getIdFather() {
        return idFather;
    }
    public void setIdFather(Integer idFather) {
        this.idFather = idFather;
    }
    public Integer getIdMother() {
        return idMother;
    }
    public void setIdMother(Integer idMother) {
        this.idMother = idMother;
    }    
}
