package com.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="PG_DATA")
@NamedQuery(name = "findDog",
    query = "SELECT f.data FROM PgDog f where idDog = :idDog")
public class PgDog extends JsonbDataType {

    @Id
    @Column
    public Integer idDog;
    
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    public Dog data;



}
