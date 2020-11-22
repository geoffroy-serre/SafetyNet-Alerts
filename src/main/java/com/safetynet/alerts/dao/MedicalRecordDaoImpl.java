package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.interfaces.IMedicalRecordDao;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;
import com.safetynet.alerts.utils.OriginalInputFile;

/**
 * The Class MedicalRecordDaoImpl.
 */
@Repository
public class MedicalRecordDaoImpl implements IMedicalRecordDao {

  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger("App");
  
  /**
   * Gets the medical record list dao.
   *
   * @param pathToData the path to data
   * @return the medical record list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public MedicalRecordList getMedicalRecordListDao(String pathToData){
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    objectMapper.registerModule(new JavaTimeModule());
    
    MedicalRecordList medicalRecordList = new MedicalRecordList();;
    try {
      medicalRecordList = objectMapper.readValue(new File(pathToData),
          MedicalRecordList.class);
    } catch (JsonParseException e) {
      logger.error("JsonParseException getting MedicalRecord List", e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException getting MedicalRecord List", e);
    } catch (IOException e) {
      logger.error("IOException getting MedicalRecord List", e);
    }
    
    logger.info("MedicalRecordList retrieval done");

    return medicalRecordList;
  }

  /**
   * Post new medical record dao.
   *
   * @return the medical record
   */
  @Override
  public MedicalRecord postNewMedicalRecordDao() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Update medical record dao.
   *
   * @return the medical record
   */
  @Override
  public MedicalRecord updateMedicalRecordDao() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Delete medical record dao.
   *
   * @return the medical record
   */
  @Override
  public MedicalRecord deleteMedicalRecordDao() {
    // TODO Auto-generated method stub
    return null;
  }

  

}
