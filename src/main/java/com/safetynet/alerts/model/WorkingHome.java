package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class WorkingHome {

  @JsonProperty("idHome")
  private UUID idHome;
  @JsonProperty("address")
  private String address;
  @JsonProperty("city")
  private String city;
  @JsonProperty("zip")
  private String zip;
  @JsonProperty("persons")
  private ArrayList<WorkingPerson> personList;

  public UUID getIdHome() {
    return idHome;
  }

  public void setIdHome(UUID idHome) {
    this.idHome = idHome;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public void setZip(String zip) {
    this.zip = zip;
  }

  public ArrayList<WorkingPerson> getPersonList() {
    return personList;
  }

  public void setPersonList(ArrayList<WorkingPerson> personList) {
    this.personList = personList;
  }

  @Override
  public String toString() {
    return "WorkingHome{"
            + "idHome=" + idHome
            + ", address='" + address + '\''
            + ", city='" + city + '\''
            + ", zip='" + zip + '\''
            + ", personList=" + personList
            + '}';
  }
}
