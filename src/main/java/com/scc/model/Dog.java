package com.scc.model;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scc.annotation.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "id"
    , "name"
    , "gender"
    , "birthDate"
    , "birthCountry"
    , "inbreedingCoefficient"
    , "pedigrees"
    , "tokens"
    , "breed"
    , "dna"
    , "health"
    , "breeder"
    , "owners"
    , "titles"
    , "mother"
    , "father"
})
public class Dog extends Hidden {

    /**
     * @author adenecheau
     * L'annotation @Role determine l'affichage de l'objet lors de la serialization
     * Pour que cet objet soit affiché, l'utilisateur connecté doit disposer de ce rôle dans keycloak
     * Selon le droit affecté à l'utilisateur, la propriété hidden est renseignée à true/false
     * Une propriété hidden renseignée à true masque l'objet dans la réponse.
     */

    @Schema(required = false, description = "id")
    private int id;
    @Schema(required = false, description = "name")
    private String name;
    @Schema(required = false, description = "gender")
    private String gender;
    @Schema(required = false, description = "date of birth")
    private String birthDate;
    @Schema(required = false, description = "country")
    private String birthCountry;
    @Schema(required = false, description = "inbreeding coefficient")
    private String inbreedingCoefficient;
    @Schema(required = false, description = "pedigrees")
    private List<Pedigree> pedigrees;
    @Schema(required = false, description = "tokens")
    private List<Token> tokens;
    @Schema(required = false, description = "breed data")
    private Breed breed;
    @Schema(required = false, description = "dna data")
    @Role(name = "dna")
    private Dna dna;
    @Schema(required = false, description = "heatlh data")
    @Role(name = "health")
    private List<Health> health;
    @Schema(required = false, description = "breeder data")
    private Breeder breeder;
    @Schema(required = false, description = "owners data")
    private List<Owner> owners;
    @Schema(required = false, description = "titles data")
    private List<Title> titles;
    @Schema(required = false, description = "Mother")
    private Dog mother;
    @Schema(required = false, description = "Father")
    private Dog father;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getBirthCountry() { return birthCountry; }
    public void setBirthCountry(String birthCountry) { this.birthCountry = birthCountry; }

    public String getInbreedingCoefficient() { return inbreedingCoefficient; }
    public void setInbreedingCoefficient(String inbreedingCoefficient) { this.inbreedingCoefficient = inbreedingCoefficient; }

    public List<Pedigree> getPedigrees() { return pedigrees; }
    public void setPedigrees(List<Pedigree> pedigrees) { this.pedigrees = pedigrees; }

    public List<Token> getTokens() { return tokens; }
    public void setTokens(List<Token> tokens) { this.tokens = tokens; }

    public Breed getBreed() { return breed; }
    public void setBreed(Breed breed) { this.breed = breed; }

    public Dna getDna() { return dna; }
    public void setDna(Dna dna) { this.dna = dna; }

    public List<Health> getHealth() { return health; }
    public void setHealth(List<Health> health) { this.health = health; }

    public Breeder getBreeder() { return breeder; }
    public void setBreeder(Breeder breeder) { this.breeder = breeder; }
    
    public List<Owner> getOwners() { return owners; }
    public void setOwners(List<Owner> owners) { this.owners = owners; }

    public List<Title> getTitles() { return titles; }
    public void setTitles(List<Title> titles) { this.titles = titles; }

    public Dog getMother() { return mother; }
    public void setMother(Dog mother) { this.mother = mother; }

    public Dog getFather() { return father; }
    public void setFather(Dog father) { this.father = father; }

    public Dog withId(int id) {
        this.setId(id);
        return this;
    }

    public Dog withName(String name) {
        this.setName(name);
        return this;
    }

    public Dog withGender(String gender) {
        this.setGender(gender);
        return this;
    }

    public Dog withBirthDate(String birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public Dog withBirthCountry(String birthCountry) {
        this.setBirthCountry(birthCountry);
        return this;
    }

    public Dog withInbreedingCoefficient(String inbreedingCoefficient) {
        this.setInbreedingCoefficient(inbreedingCoefficient);
        return this;
    }

    public Dog withPedigrees(List<Pedigree> pedigrees) {
        this.setPedigrees(pedigrees);
        return this;
    }

    public Dog withTokens(List<Token> tokens) {
        this.setTokens(tokens);
        return this;
    }

    public Dog withBreed(Breed breed) {
        this.setBreed(breed);
        return this;
    }

    public Dog withDna(Dna dna) {
        this.setDna(dna);
        return this;
    }

    public Dog withHealth(List<Health> health) {
        this.setHealth(health);
        return this;
    }
    
    public Dog withBreeder(Breeder breeder) {
        this.setBreeder(breeder);
        return this;
    }

    public Dog withOwners(List<Owner> owners) {
        this.setOwners(owners);
        return this;
    }

    public Dog withTitles(List<Title> titles) {
        this.setTitles(titles);
        return this;
    }

    public Dog withFather(Dog father) {
        this.setFather(father);
        return this;
    }

    public Dog withMother(Dog mother) {
        this.setMother(mother);
        return this;
    }
    
    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", gender=" + gender + ", birthDate=" + birthDate
                + ", birthCountry=" + birthCountry + ", inbreedingCoefficient=" + inbreedingCoefficient + ", pedigrees="
                + pedigrees + ", tokens=" + tokens + ", breed=" + breed + ", dna=" + dna + ", health=" + health
                + ", breeder=" + breeder + ", owners=" + owners + ", titles=" + titles + ", mother=" + mother
                + ", father=" + father + "}";
    }    

}
