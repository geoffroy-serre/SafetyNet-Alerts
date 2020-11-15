package com.safetynet.alerts.dto;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.interfaces.IFireStationDto;
import com.safetynet.alerts.model.FireStationList;
import com.safetynet.alerts.utils.OriginalInputFile;

@Component
public class FireStationDtoImpl implements IFireStationDto{
  
  private static final Logger logger = LogManager.getLogger("App");


  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public FireStationList getFireStationListDto()
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    objectMapper.registerModule(new JavaTimeModule());
    
    FireStationList fireStationList = objectMapper.readValue(new File(OriginalInputFile.getOriginalInputFile()),
        FireStationList.class);
    
    logger.info("MedicalRecordList retrieval done");

    return fireStationList;

  }

}
