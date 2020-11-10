package com.safetynet.alerts.model;

import java.util.UUID;

public class Firestation {
  
  private UUID id = UUID.randomUUID();
  private int stationNumber;
  private UUID home;
  
  public UUID getId() {
    return id;
  }
  public void setId(UUID pId) {
    this.id = pId;
  }
  public int getStationNumber() {
    return stationNumber;
  }
  public void setStationNumber(int pStationNumber) {
    this.stationNumber = pStationNumber;
  }
  public UUID getHome() {
    return home;
  }
  public void setHome(UUID pHome) {
    this.home = pHome;
  }
  
  

}
