package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Person {

  
  private UUID id ;
  private String firstName;
  private String lastName;
  @JsonProperty("phone")
  private String cellNumber;
  private LocalDate birthdate;
  private String email;
  private String address;
  private String city;
  private String zip;
  private UUID idHome;
  private UUID idMedicalRecord;
  private int age;
  
  
  public UUID getId() {
    return id;
  }

  public void setId(UUID pId) {
    this.id = pId;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String pLastName) {
    this.lastName = pLastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String pFirstName) {
    this.firstName = pFirstName;
  }

  public String getCellNumber() {
    return cellNumber;
  }

  public void setCellNumber(String pCellNumber) {
    this.cellNumber = pCellNumber;
  }

  public LocalDate getBirthDate() {
    return birthdate;
  }

  public void setBirthDate(LocalDate pBirthdate) {
    this.birthdate = pBirthdate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String pEmail) {
    this.email = pEmail;
  }

  public UUID getIdHome() {
    return idHome;
  }

  public void setIdHome(UUID pIdHome) {
    this.idHome = pIdHome;
  }

  public UUID getIdMedicalRecord() {
    return idMedicalRecord;
  }

  public void setIdMedicalRecord(UUID pIdMedicalRecord) {
    this.idMedicalRecord = pIdMedicalRecord;

  }

  public int getAge() {
    return age;
  }

  public void setAge(int pAge) {
    this.age = pAge;
  }

  @Override
  public String toString() {
    return String.format(
        "Person [id=%s, firstName=%s, lastName=%s, email=%s, idMedicalRecord=%s, idHome=%s; birthdate=%s]",
        id, firstName, lastName, email, idMedicalRecord, idHome, birthdate);
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String pAdress) {
    this.address = pAdress;
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

  public void setZip(String zipcode) {
    this.zip = zipcode;
  }

}
