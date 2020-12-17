package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
/**
 * Contains Original format data
 * @param String firstName;
 *@param String lastName;
 *@param LocalDate birthdate;
 *@param ArrayList<String> medications;
 *@param ArrayList<String> allergies;
 */
public class OriginalMedicalrecord {


  @JsonProperty("firstName")
  @NotBlank
  @NotNull
  private String firstName;

  @JsonProperty("lastName")
  @NotBlank
  @NotNull
  private String lastName;

  @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonProperty("birthdate")
  @NotNull
  private LocalDate birthdate;

  @JsonProperty("medications")
  @NotNull
  private ArrayList<String> medications;

  @JsonProperty("allergies")
  @NotNull
  private ArrayList<String> allergies;

  public ArrayList<String> getAllergies() {
    return allergies;
  }

  public void setAllergies(ArrayList<String> allergies) {
    this.allergies = allergies;
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

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public ArrayList<String> getMedications() {
    return medications;
  }

  public void setMedications(ArrayList<String> medications) {
    this.medications = medications;
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