package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
/**
 * Wrapper for output data
 * @param ArrayList<OutPutPerson> persons;
 *@param ArrayList<OutPutFireStation> firestations;
 *@param ArrayList<OutPutMedicalRecord> medicalrecords;
 *@param  ArrayList<OutPutHome> homes;
 */
public class OutPutResponse {


  @JsonProperty("persons")
  private ArrayList<OutPutPerson> persons;

  @JsonProperty("firestations")
  private ArrayList<OutPutFireStation> firestations;

  @JsonProperty("medicalrecords")
  private ArrayList<OutPutMedicalRecord> medicalrecords;

  @JsonProperty("home")
  private ArrayList<OutPutHome> homes;

  public ArrayList<OutPutPerson> getPersons() {
    return persons;
  }

  public void setPersons(ArrayList<OutPutPerson> persons) {
    this.persons = persons;
  }

  public ArrayList<OutPutFireStation> getFirestations() {
    return firestations;
  }

  public void setFirestations(ArrayList<OutPutFireStation> firestations) {
    this.firestations = firestations;
  }

  public ArrayList<OutPutMedicalRecord> getMedicalrecords() {
    return medicalrecords;
  }

  public void setMedicalrecords(ArrayList<OutPutMedicalRecord> medicalrecords) {
    this.medicalrecords = medicalrecords;
  }

  public ArrayList<OutPutHome> getHomes() {
    return homes;
  }

  public void setHomes(ArrayList<OutPutHome> homes) {
    this.homes = homes;
  }
}
