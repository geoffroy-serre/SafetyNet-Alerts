package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IMedicalRecordDao;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;
@Component
public class MedicalRecordService {
  @Autowired
  private IMedicalRecordDao medicalRecordDao;
  
  public MedicalRecordList getMedicalRecordsList() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return medicalRecordDao.getMedicalRecordListDto();
  }

  
  
  public MedicalRecord postNewMedicalRecord() {
    return medicalRecordDao.postNewMedicalRecord();
    
  }
  
  public MedicalRecord updateMedicalRecord() {
    return medicalRecordDao.updateMedicalRecord();
    
  }
  
  public MedicalRecord deleteMedicalRecord() {
    return medicalRecordDao.deleteMedicalRecord();
    
  }

}
