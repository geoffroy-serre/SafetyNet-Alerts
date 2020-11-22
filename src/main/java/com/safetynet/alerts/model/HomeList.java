package com.safetynet.alerts.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HomeList {

  private ArrayList<Home> home = new ArrayList<Home>();

  public ArrayList<Home> getHome() {
    return home;
  }

  public void setHome(ArrayList<Home> pHome) {
    this.home = pHome;
  }

  public void addHome(Home pHome) {
    this.home.add(pHome);
  }

 

  @Override
  public String toString() {
    return home.toString();
  }
  
  public ArrayList<Home> getHomeList(){return home;}
  
  

}
