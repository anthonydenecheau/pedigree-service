package com.scc.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.scc.model.Title;

@ApplicationScoped
public class TitleService extends AbstractGenericService<Title> {

    public TitleService() {
        super();
        this.setType(Title.class);
    }

    public List<Title> findTitlesByIdDog(int id) {

        List<Title> _l = new ArrayList<Title>();

        try {
            
        } catch (Exception e) {
        } finally {
        }
        return _l;
    }
}
