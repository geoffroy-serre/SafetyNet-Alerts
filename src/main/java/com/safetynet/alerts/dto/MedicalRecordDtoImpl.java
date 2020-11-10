package com.safetynet.alerts.dto;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.interfaces.IMedicalRecordDto;
import com.safetynet.alerts.model.MedicalRecordList;

@Component
public class MedicalRecordDtoImpl implements IMedicalRecordDto {

  private static final Logger logger = LogManager.getLogger("App");
  
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public MedicalRecordList getMedicalRecordListDto()
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    DateFormat df = new SimpleDateFormat("MM-dd-YYYY");
    objectMapper.setDateFormat(df);
    
    MedicalRecordList medicalRecordList = objectMapper.readValue(new File("data.json"),
        MedicalRecordList.class);
    
    logger.info("MedicalRecordList retrieval");
//logger.info(trucs.toString());
    return medicalRecordList;
  }
  
  

}
