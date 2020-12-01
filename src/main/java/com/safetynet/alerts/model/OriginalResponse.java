package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.alerts.interfaces.Response;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class OriginalResponse implements Response {

  @JsonProperty("persons")
  private ArrayList<OriginalPersons> persons;

  @JsonProperty("firestations")
  private ArrayList<OriginalFirestation> firestations;

  @JsonProperty("medicalrecords")
  private ArrayList<OriginalMedicalrecords> medicalrecords;

  @Override
  public ArrayList<OriginalPersons> getPersons() {
    return persons;
  }

  public void setPersons(ArrayList<OriginalPersons> persons) {
    this.persons = persons;
  }

  @Override
  public ArrayList<OriginalFirestation> getFirestations() {
    return firestations;
  }

  public void setFirestations(ArrayList<OriginalFirestation> firestations) {
    this.firestations = firestations;
  }

  @Override
  public ArrayList<OriginalMedicalrecords> getMedicalrecords() {
    return medicalrecords;
  }

  public void setMedicalrecords(ArrayList<OriginalMedicalrecords> medicalrecords) {
    this.medicalrecords = medicalrecords;
  }

  @Override
  public String toString() {
    return
            "OriginalResponse{"
                    + "persons = '" + persons + '\''
                    + ",firestations = '" + firestations + '\''
                    + ",medicalrecords = '" + medicalrecords + '\''
                    + "}";
  }
}