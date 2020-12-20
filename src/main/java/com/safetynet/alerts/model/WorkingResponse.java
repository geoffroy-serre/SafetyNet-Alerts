package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 * Wrapper for Working data
 */
@Component
public class WorkingResponse {

  @JsonProperty("persons")
  private ArrayList<WorkingPerson> persons;

  @JsonProperty("firestations")
  private ArrayList<WorkingFireStation> firestations;

  @JsonProperty("medicalrecords")
  private ArrayList<WorkingMedicalRecord> medicalrecords;

  @JsonProperty("home")
  private ArrayList<WorkingHome> homes;

  public ArrayList<WorkingHome> getHomes() {
    return homes;
  }

  public void setHomes(ArrayList<WorkingHome> homes) {
    this.homes = homes;
  }

  public ArrayList<WorkingPerson> getPersons() {
    return persons;
  }

  public void setPersons(ArrayList<WorkingPerson> persons) {
    this.persons = persons;
  }

  public ArrayList<WorkingFireStation> getFirestations() {
    return firestations;
  }

  public void setFirestations(ArrayList<WorkingFireStation> firestations) {
    this.firestations = firestations;
  }

  public ArrayList<WorkingMedicalRecord> getMedicalrecords() {
    return medicalrecords;
  }

  public void setMedicalrecords(ArrayList<WorkingMedicalRecord> medicalrecords) {
    this.medicalrecords = medicalrecords;
  }

  @Override
  public String toString() {
    return "WorkingResponse{"
            + "persons=" + persons
            + ", firestations=" + firestations
            + ", medicalrecords=" + medicalrecords
            + '}';
  }
}