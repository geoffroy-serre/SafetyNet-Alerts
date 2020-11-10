package com.safetynet.alerts.model;

import java.util.ArrayList;

public class MedicalRecordList {
  
  public ArrayList<MedicalRecord> medicalRecord;

  public ArrayList<MedicalRecord> getMedicalRecord() {
      return medicalRecord;
  }

  public void setMedicalRecord(ArrayList<MedicalRecord> pMedicalRecords) {
      this.medicalRecord = pMedicalRecords;
  }

  @Override
  public String toString() {
      return  medicalRecord.toString() ;
  }

}
