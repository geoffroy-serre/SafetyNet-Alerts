package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IMedicalRecordDao;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;

@Service
public class MedicalRecordService {
  @Autowired
  private IMedicalRecordDao medicalRecordDao;
  
  public MedicalRecordList getMedicalRecordsListService() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return medicalRecordDao.getMedicalRecordListDao();
  }

  
  
  public MedicalRecord postNewMedicalRecordService() {
    return medicalRecordDao.postNewMedicalRecordDao();
    
  }
  
  public MedicalRecord updateMedicalRecordService() {
    return medicalRecordDao.updateMedicalRecordDao();
    
  }
  
  public MedicalRecord deleteMedicalRecordService() {
    return medicalRecordDao.deleteMedicalRecordDao();
    
  }

}
