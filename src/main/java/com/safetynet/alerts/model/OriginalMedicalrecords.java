package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OriginalMedicalrecords {


  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("birthdate")
  private String birthdate;

  @JsonProperty("medications")
  private List<String> medications;

  @JsonProperty("allergies")
  private List<String> allergies;

  public void setAllergies(List<String> allergies) {
    this.allergies = allergies;
  }

  public List<String> getAllergies() {
    return allergies;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setMedications(List<String> medications) {
    this.medications = medications;
  }

  public List<String> getMedications() {
    return medications;
  }

  @Override
  public String toString() {
    return
            "MedicalrecordsItem{"
                    + "allergies = '" + allergies + '\''
                    + ",firstName = '" + firstName + '\''
                    + ",lastName = '" + lastName + '\''
                    + ",birthdate = '" + birthdate + '\''
                    + ",medications = '" + medications + '\''
                    + "}";
  }
}