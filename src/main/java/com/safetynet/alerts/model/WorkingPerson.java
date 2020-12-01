package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;

public class WorkingPerson {

  @JsonProperty("id")
  private UUID id;
  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("lastName")
  private String lastName;
  @JsonProperty("homeId")
  private UUID homeID;
  @JsonProperty("phone")
  private String phone;
  @JsonProperty("birthdate")
  private LocalDate birthdate;
  @JsonProperty("age")
  private int age;
  @JsonProperty("email")
  private String email;
  @JsonProperty("idMedicalRecord")
  private UUID idMedicalRecord;


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UUID getHomeID() {
    return homeID;
  }

  public void setHomeID(UUID homeID) {
    this.homeID = homeID;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UUID getIdMedicalRecord() {
    return idMedicalRecord;
  }

  public void setIdMedicalRecord(UUID idMedicalRecord) {
    this.idMedicalRecord = idMedicalRecord;
  }

  @Override
  public String toString() {
    return "WorkingPerson{"
            + "id=" + id
            + ", firstName='" + firstName + '\''
            + ", lastName='" + lastName + '\''
            + ", homeID=" + homeID
            + ", phone='" + phone + '\''
            + ", birthdate=" + birthdate
            + ", age=" + age
            + ", email='" + email + '\''
            + ", idMedicalRecord=" + idMedicalRecord
            + '}';
  }
}
