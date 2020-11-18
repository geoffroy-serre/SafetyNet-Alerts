package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FireStation {
  
  private UUID id ;
  private int station;
  private UUID home;
  private String address;
  private ArrayList<String> addressFireStationList;
  
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
 
  public ArrayList<String> getAddressFireStationList() {
    return addressFireStationList;
  }
  public void setAddressFireStationList(ArrayList<String> addressFireStationList) {
    this.addressFireStationList = addressFireStationList;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String adress) {
    this.address = adress;
  }
  
  
  

}
