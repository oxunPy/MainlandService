package uz.akfa.regionservice.communityService.models;

import java.util.Set;

public class Community {

    public Community(String name) {
        this.name = name;
    }

    public Community(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    private String name;

    private Set<Person> personSet;



}
