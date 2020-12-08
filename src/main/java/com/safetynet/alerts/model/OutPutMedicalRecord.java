package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OutPutMedicalRecord {

  @JsonProperty("medications")
  private List<String> medications;

  @JsonProperty("allergies")
  private List<String> allergies;

  public List<String> getMedications() {
    return medications;
  }

  public void setMedications(List<String> medications) {
    this.medications = medications;
  }

  public List<String> getAllergies() {
    return allergies;
  }

  public void setAllergies(List<String> allergies) {
    this.allergies = allergies;
  }

  @Override
  public String toString() {
    return "OutPutMedicalRecord{" +
            "medications=" + medications +
            ", allergies=" + allergies +
            '}';
  }
}