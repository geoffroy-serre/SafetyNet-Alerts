package com.safetynet.alerts.model;

import java.util.UUID;

public class FireStation {
  
  private UUID id ;
  private int station;
  private UUID home;
  private String address;
  
  public UUID getId() {
    return id;
  }
  public void setId(UUID pId) {
    this.id = pId;
  }
  public int getStation() {
    return station;
  }
  public void setStation(int pStation) {
    this.station = pStation;
  }
  public UUID getHome() {
    return home;
  }
  public void setHome(UUID pHome) {
    this.home = pHome;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  
  

}
