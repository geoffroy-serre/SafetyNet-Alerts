package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;


/**
 * The Interface IMedicalRecordDao.
 */
public interface IMedicalRecordDao {
  
  /**
   * Gets the medical record list dao.
   *
   * @param pathToData the path to data
   * @return the medical record list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public MedicalRecordList getMedicalRecordListDao(String pathToData) throws JsonParseException, JsonMappingException, IOException;

  /**
   * Post new medical record dao.
   *
   * @return the medical record
   */
  public MedicalRecord postNewMedicalRecordDao();

  /**
   * Update medical record dao.
   *
   * @return the medical record
   */
  public MedicalRecord updateMedicalRecordDao();

  /**
   * Delete medical record dao.
   *
   * @return the medical record
   */
  public MedicalRecord deleteMedicalRecordDao();

}
