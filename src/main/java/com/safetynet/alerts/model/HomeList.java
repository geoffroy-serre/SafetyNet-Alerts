package com.safetynet.alerts.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HomeList {
   
    
    public ArrayList<Home> home;

    public ArrayList<Home> getHome() {
        return home;
    }

    public void setHome(ArrayList<Home> pHome) {
        this.home = pHome;
    }
    
   
    public void addHome(Home pHome) {
      home.add(pHome);
    }

    @Override
    public String toString() {
        return  home.toString() ;
    }

}
