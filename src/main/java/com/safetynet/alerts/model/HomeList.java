package com.safetynet.alerts.model;

import java.util.ArrayList;

public class HomeList {
   
    public ArrayList<Home> home;

    public ArrayList<Home> getPerson() {
        return home;
    }

    public void setPerson(ArrayList<Home> pHome) {
        this.home = pHome;
    }

    @Override
    public String toString() {
        return  home.toString() ;
    }

}
