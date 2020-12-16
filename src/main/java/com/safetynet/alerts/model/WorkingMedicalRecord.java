package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class WorkingMedicalRecord {

  @JsonProperty("idMedicalRecord")
  private UUID idMedicalRecord;

  @JsonProperty("medications")
  private ArrayList<String> medications;

  @JsonProperty("allergies")
  private ArrayList<String> allergies;

  public UUID getIdMedicalRecord() {
    return idMedicalRecord;
  }

  public void setIdMedicalRecord(UUID idMedicalRecord) {
    this.idMedicalRecord = idMedicalRecord;
  }

  public ArrayList<String> getMedications() {
    return medications;
  }

  public void setMedications(ArrayList<String> medications) {
    this.medications = medications;
  }

  public ArrayList<String> getAllergies() {
    return allergies;
  }

  public void setAllergies(ArrayList<String> allergies) {
    this.allergies = allergies;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof WorkingMedicalRecord)) {
      return false;
    }
    WorkingMedicalRecord workingMedicalRecord = (WorkingMedicalRecord) obj;
    return  Objects.equals(idMedicalRecord,workingMedicalRecord.idMedicalRecord)&&
            Objects.equals(allergies, workingMedicalRecord.allergies) &&
            Objects.equals(medications, workingMedicalRecord.medications);

  }

  @Override
  public int hashCode() {
    return Objects.hash(idMedicalRecord,allergies,medications);
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
