package com.safetynet.alerts.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class PersonList {
  
  
    public ArrayList<Person> persons;

    public ArrayList<Person> getPerson() {
        return persons;
    }

    public void setPerson(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return  persons.toString() ;
    }




}
