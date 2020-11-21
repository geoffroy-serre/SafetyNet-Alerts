package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;


public interface IMedicalRecordDao {
  
  public MedicalRecordList getMedicalRecordListDao(String pathToData) throws JsonParseException, JsonMappingException, IOException;

  public MedicalRecord postNewMedicalRecordDao();

  public MedicalRecord updateMedicalRecordDao();

  public MedicalRecord deleteMedicalRecordDao();

}
