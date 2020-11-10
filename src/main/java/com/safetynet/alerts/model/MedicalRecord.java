package com.safetynet.alerts.model;

import java.util.List;
import java.util.UUID;

public class MedicalRecord {

  private UUID id = UUID.randomUUID();
  private List<String> allergy;
  private List<String> medication;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<String> getAllergy() {
    return allergy;
  }

  public void setAllergy(List<String> allergy) {
    this.allergy = allergy;
  }

  public List<String> getMedication() {
    return medication;
  }

  public void setMedication(List<String> medication) {
    this.medication = medication;
  }

  @Override
  public String toString() {
    return String.format("MedicalRecord [id=%s, allergy=%s, medication=%s]", id,
        allergy, medication);
  }

}
