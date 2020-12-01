package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class WorkingMedicalRecord {

  @JsonProperty("idMedicalRecord")
  private UUID idMedicalRecord;

  @JsonProperty("medications")
  private List<String> medications;

  @JsonProperty("allergies")
  private List<String> allergies;

  public UUID getIdMedicalRecord() {
    return idMedicalRecord;
  }

  public void setIdMedicalRecord(UUID idMedicalRecord) {
    this.idMedicalRecord = idMedicalRecord;
  }

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
    return "WorkingMedicalRecord{"
            + "idMedicalRecord=" + idMedicalRecord
            + ", medications=" + medications
            + ", allergies=" + allergies
            + '}';
  }
}
