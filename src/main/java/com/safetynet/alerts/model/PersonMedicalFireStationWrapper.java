package com.safetynet.alerts.model;

public class PersonMedicalFireStationWrapper {
  
  
  private Person person;
  private MedicalRecord medicalRecord;
  private FireStation firestation;
  
  public Person getPerson() {
    return person;
  }
  public void setPerson(Person person) {
    this.person = person;
  }
  public MedicalRecord getMedicalRecord() {
    return medicalRecord;
  }
  public void setMedicalRecord(MedicalRecord medicalRecord) {
    this.medicalRecord = medicalRecord;
  }
  
  @Override
  public String toString() {
    return person.toString()+" "+medicalRecord.toString();
    
  }
  public FireStation getFirestation() {
    return firestation;
  }
  public void setFirestation(FireStation firestation) {
    this.firestation = firestation;
  }

}
