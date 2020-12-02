package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.alerts.interfaces.Response;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class WorkingResponse {

  @JsonProperty("persons")
  private ArrayList<WorkingPerson> persons;

  @JsonProperty("firestations")
  private ArrayList<WorkingFireStation> firestations;

  @JsonProperty("medicalrecords")
  private ArrayList<WorkingMedicalRecord> medicalrecords;

  public void setPersons(ArrayList<WorkingPerson> persons) {
    this.persons = persons;
  }

  public ArrayList<WorkingPerson> getPersons() {
    return persons;
  }

  public void setFirestations(ArrayList<WorkingFireStation> firestations) {
    this.firestations = firestations;
  }

  public ArrayList<WorkingFireStation> getFirestations() {
    return firestations;
  }

  public void setMedicalrecords(ArrayList<WorkingMedicalRecord> medicalrecords) {
    this.medicalrecords = medicalrecords;
  }

  public ArrayList<WorkingMedicalRecord> getMedicalrecords() {
    return medicalrecords;
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