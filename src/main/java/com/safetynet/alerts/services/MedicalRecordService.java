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

/**
 * The Class MedicalRecordService.
 */
@Service
public class MedicalRecordService {
  
  /** The medical record dao. */
  @Autowired
  private IMedicalRecordDao medicalRecordDao;
  
  /**
   * Gets the medical records list service.
   *
   * @param pathToData the path to data
   * @return the medical records list service
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws NoSuchFileException the no such file exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public MedicalRecordList getMedicalRecordsListService(String pathToData) throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return medicalRecordDao.getMedicalRecordListDao(pathToData);
  }

  
  
  /**
   * Post new medical record service.
   *
   * @return the medical record
   */
  public MedicalRecord postNewMedicalRecordService() {
    return medicalRecordDao.postNewMedicalRecordDao();
    
  }
  
  /**
   * Update medical record service.
   *
   * @return the medical record
   */
  public MedicalRecord updateMedicalRecordService() {
    return medicalRecordDao.updateMedicalRecordDao();
    
  }
  
  /**
   * Delete medical record service.
   *
   * @return the medical record
   */
  public MedicalRecord deleteMedicalRecordService() {
    return medicalRecordDao.deleteMedicalRecordDao();
    
  }

}
