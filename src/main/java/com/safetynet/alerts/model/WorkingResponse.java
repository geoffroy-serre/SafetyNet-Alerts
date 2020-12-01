package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.alerts.interfaces.Response;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class WorkingResponse implements Response {

  @JsonProperty("persons")
  private ArrayList<?> persons;

  @JsonProperty("firestations")
  private ArrayList<?> firestations;

  @JsonProperty("medicalrecords")
  private ArrayList<?> medicalrecords;

  public void setPersons(ArrayList<?> persons) {
    this.persons = persons;
  }

  public ArrayList<?> getPersons() {
    return persons;
  }

  public void setFirestations(ArrayList<?> firestations) {
    this.firestations = firestations;
  }

  public ArrayList<?> getFirestations() {
    return firestations;
  }

  public void setMedicalrecords(ArrayList<?> medicalrecords) {
    this.medicalrecords = medicalrecords;
  }

  public ArrayList<?> getMedicalrecords() {
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