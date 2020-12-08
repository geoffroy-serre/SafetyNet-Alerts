package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class OutPutHome {

  @JsonProperty("address")
  private String address;
  @JsonProperty("city")
  private String city;
  @JsonProperty("zip")
  private String zip;
  @JsonProperty("persons")
  private ArrayList<OutPutPerson> persons = new ArrayList<>();

  public ArrayList<OutPutPerson> getPersons() {
    return persons;
  }

  public void setPersons(ArrayList<OutPutPerson> persons) {
    this.persons = persons;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OutPutHome that = (OutPutHome) o;
    return address.equals(that.address) &&
            city.equals(that.city) &&
            zip.equals(that.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, city, zip);
  }
}
