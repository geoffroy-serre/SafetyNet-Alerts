package com.safetynet.alerts.model;

public class Firestation {
  
  private int id;
  private int stationNumber;
  private Home home;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getStationNumber() {
    return stationNumber;
  }
  public void setStationNumber(int stationNumber) {
    this.stationNumber = stationNumber;
  }
  public Home getHome() {
    return home;
  }
  public void setHome(Home home) {
    this.home = home;
  }
  
  

}
