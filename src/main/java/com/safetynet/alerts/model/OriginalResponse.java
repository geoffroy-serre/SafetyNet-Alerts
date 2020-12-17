package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
/**
 * Wrapper for Original datas
 * @param ArrayList<OriginalPerson> persons;
 *@param ArrayList<OriginalFirestation> firestations;
 *@param ArrayList<OriginalMedicalrecord> medicalrecords;
 */
public class OriginalResponse {

  @JsonProperty("persons")
  private ArrayList<OriginalPerson> persons;

  @JsonProperty("firestations")
  private ArrayList<OriginalFirestation> firestations;

  @JsonProperty("medicalrecords")
  private ArrayList<OriginalMedicalrecord> medicalrecords;


  public ArrayList<OriginalPerson> getPersons() {
    return persons;
  }

  public void setPersons(ArrayList<OriginalPerson> persons) {
    this.persons = persons;
  }


  public ArrayList<OriginalFirestation> getFirestations() {
    return firestations;
  }

  public void setFirestations(ArrayList<OriginalFirestation> firestations) {
    this.firestations = firestations;
  }


  public ArrayList<OriginalMedicalrecord> getMedicalrecords() {
    return medicalrecords;
  }

  public void setMedicalrecords(ArrayList<OriginalMedicalrecord> medicalrecords) {
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