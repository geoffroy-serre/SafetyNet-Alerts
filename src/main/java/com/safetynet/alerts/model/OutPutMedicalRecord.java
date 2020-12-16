package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
/**
 * Contains OutPut format data.
 * @param UUID idMedicalRecord;
 * @param List<String> medications;
 *@param List<String> allergies;
 */
public class OutPutMedicalRecord {

  @JsonProperty("idMedicalRecord")
  private UUID idMedicalRecord;
  @JsonProperty("medications")
  private List<String> medications;

  @JsonProperty("allergies")
  private List<String> allergies;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    return "OutPutMedicalRecord{" +
            "medications=" + medications +
            ", allergies=" + allergies +
            '}';
  }
}

