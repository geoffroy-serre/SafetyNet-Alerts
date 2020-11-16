package com.safetynet.alerts.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;


public class PersonList {
   
    public ArrayList<Person> person;

    public ArrayList<Person> getPerson() {
        return person;
    }

    public void setPerson(ArrayList<Person> pPerson) {
        this.person = pPerson;
    }

    @Override
    public String toString() {
        return  person.toString() ;
    }

}
