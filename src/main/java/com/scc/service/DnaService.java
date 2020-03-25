package com.scc.service;

import javax.enterprise.context.ApplicationScoped;

import com.scc.model.Dna;

@ApplicationScoped
public class DnaService extends AbstractGenericService<Dna> {

    public DnaService() {
        super();
        this.setType(Dna.class);
    }

    public Dna findDNAByIdDog(int id) {

        //System.out.println("Start readDNA");
        Dna _a = new Dna();

        try {
            //[TODO] si pas de résultat santé + n'a pas le role dna
            // - créer un record unique avec hidden=true
            // > la propriété health sera masquée
            _a.setCode("A");
            _a.setLibelle("IDENTIFICATION REALISEE");
            /*if (hasRole())
                _a.setHidden(false);
            else
                _a.setHidden(true);
            */

        } catch (Exception e) {
            //System.out.println("Error readDNA" + e.getMessage());
        } finally {
            //System.out.println("End readDNA");
        }
        return _a;
    }
}
