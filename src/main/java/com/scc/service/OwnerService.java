package com.scc.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.scc.model.Owner;

@ApplicationScoped
public class OwnerService extends AbstractGenericService<Owner> {

    public OwnerService() {
        super();
        this.setType(Owner.class);
    }

    public List<Owner> findOwnersByIdDog(int id) {

        List<Owner> _l = new ArrayList<Owner>();

        try {
        } catch (Exception e) {
        } finally {
        }
        return _l;
    }
}
