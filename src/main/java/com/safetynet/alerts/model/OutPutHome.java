package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
/**
 * Contains output format data
 * @param Integer stationNumber = null;
 * @param String address;
 * @param String city;
 * @param  String zip;
 * @param Integer numberOfAdults;
 * @param Integer numberOfChildren;
 * @param UUID idHome = new UUID(0L,0L);
 */
public class OutPutHome {


  @JsonProperty("stationNumber")
  private Integer stationNumber = null;
  @JsonProperty("address")
  private String address;
  @JsonProperty("city")
  private String city;
  @JsonProperty("zip")
  private String zip;
  @JsonProperty("adults")
  private Integer numberOfAdults;
  @JsonProperty("children")
  private Integer numberOfChildren;
  @JsonProperty("idHome")
  private UUID idHome = new UUID(0L, 0L);

  @JsonProperty("persons")
  private ArrayList<OutPutPerson> persons;

  public ArrayList<OutPutPerson> getPersons() {
    return persons;
  }

  public void setPersons(ArrayList<OutPutPerson> persons) {
    this.persons = persons;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public UUID getIdHome() {
    return idHome;
  }

  public void setIdHome(UUID idHome) {
    this.idHome = idHome;
  }

  public Integer getNumberOfAdults() {
    return numberOfAdults;
  }

  public void setNumberOfAdults(Integer numberOfAdults) {
    this.numberOfAdults = numberOfAdults;
  }

  public Integer getNumberOfChildren() {
    return numberOfChildren;
  }

  public void setNumberOfChildren(Integer numberOfChildren) {
    this.numberOfChildren = numberOfChildren;
  }

  public Integer getStationNumber() {
    return stationNumber;
  }

  public void setStationNumber(Integer stationNumber) {
    this.stationNumber = stationNumber;
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

  @Override
  public String toString() {
    return "OutPutHome{" +
            "stationNumber=" + stationNumber +
            ", address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", zip='" + zip + '\'' +
            ", numberOfAdults=" + numberOfAdults +
            ", numberOfChildren=" + numberOfChildren +
            ", idHome=" + idHome +
            ", persons=" + persons +
            '}';
  }
}
