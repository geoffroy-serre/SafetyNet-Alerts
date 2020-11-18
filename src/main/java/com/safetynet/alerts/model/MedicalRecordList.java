package com.safetynet.alerts.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class MedicalRecordList {
  
  public ArrayList<MedicalRecord> medicalRecord;

  public ArrayList<MedicalRecord> getMedicalRecord() {
      return medicalRecord;
  }

  public void setMedicalRecord(ArrayList<MedicalRecord> pMedicalRecord) {
      this.medicalRecord = pMedicalRecord;
  }

  @Override
  public String toString() {
      return  medicalRecord.toString() ;
  }

}
