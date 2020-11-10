package com.safetynet.alerts.model;

import java.util.UUID;

public class Home {

  private UUID id = UUID.randomUUID();
  private String adress;
  private String city;
  private String zip;
  private int idFirestation;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

 

  public String getAdress() {
    return adress;
  }

  public void setAdress(String pAdress) {
    this.adress = pAdress;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
 
  public String getZip() {
    return zip;
  }

  public void setZip(String pZip) {
    this.zip = pZip;
  }

  public int getIdFirestation() {
    return idFirestation;
  }

  public void setIdFirestation(int idFirestation) {
    this.idFirestation = idFirestation;
  }

  @Override
  public String toString() {
    return String.format(
        "Person [id=%s, Adress=%s, City=%s,  Zip=%s, FirestationId=%s]",
        id, adress, city, zip, idFirestation);
  }
}
