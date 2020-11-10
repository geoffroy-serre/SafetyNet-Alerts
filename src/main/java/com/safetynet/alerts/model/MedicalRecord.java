package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class MedicalRecord {

  
  private UUID id = UUID.randomUUID();
  private String firstName;
  private String lastName;
  private LocalDate birthdate;
  private ArrayList<String> allergies;
  private ArrayList<String> medications;

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
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

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ArrayList<String> getAllergies() {
    return allergies;
  }

  public void setAllergies(ArrayList<String> pAllergies) {
    this.allergies = pAllergies;
  }

  public ArrayList<String> getMedication() {
    return medications;
  }

  public void setMedications(ArrayList<String> pMedications) {
    this.medications = pMedications;
  }

  @Override
  public String toString() {
    return String.format(
        "MedicalRecord [id=%s,firstName=%s, lastName=%s allergies=%s, medications=%s]",
        id, firstName, lastName, allergies.toString(), medications.toString());
  }

}
