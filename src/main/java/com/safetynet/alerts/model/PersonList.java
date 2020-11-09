package com.safetynet.alerts.model;

import java.util.ArrayList;

public class PersonList {
  
  
    ArrayList<Person> persons;

    public ArrayList<Person> getPerson() {
        return persons;
    }

    public void setPerson(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "CarResponse{" +
                "cars=" + persons +
                '}';
    }




}
