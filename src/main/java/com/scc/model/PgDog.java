package com.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="PG_DATA")
@NamedQuery(name = "findDog",
    query = "SELECT f.data FROM PgDog f where idDog = :idDog")
@TypeDef(name = "MyJsonType",
    typeClass = MyJsonType.class,
    defaultForType = MyJsonType.class)
public class PgDog extends PanacheEntityBase {
    
    @Id
    @Column
    public Long idDog;

    @Column
    @Type(type = "MyJsonType")
    public Dog data;
    
    public Long getIdDog() {
        return idDog;
    }

    public void setIdDog(Long idDog) {
        this.idDog = idDog;
    }

    public Dog getData() {
        return data;
    }

    public void setData(Dog data) {
        this.data = data;
    }
}
