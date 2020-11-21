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

@Repository
public class MedicalRecordDaoImpl implements IMedicalRecordDao {

  private static final Logger logger = LogManager.getLogger("App");
  
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public MedicalRecordList getMedicalRecordListDao(String pathToData)
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    objectMapper.registerModule(new JavaTimeModule());
    
    MedicalRecordList medicalRecordList = objectMapper.readValue(new File(pathToData),
        MedicalRecordList.class);
    
    logger.info("MedicalRecordList retrieval done");

    return medicalRecordList;
  }

  @Override
  public MedicalRecord postNewMedicalRecordDao() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MedicalRecord updateMedicalRecordDao() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MedicalRecord deleteMedicalRecordDao() {
    // TODO Auto-generated method stub
    return null;
  }

  

}
