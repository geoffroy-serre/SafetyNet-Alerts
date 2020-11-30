package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.DataType;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveData;
import com.safetynet.alerts.model.OriginalResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class RetrieveDataImpl implements RetrieveData {
  private static final Logger logger = LogManager.getLogger("App");

  @Override
  public OriginalResponse getOriginalData() {
    String filePath = FilesPath.ORIGINAL_INPUT_FILE;
    ObjectMapper objectMapper = new ObjectMapper();
    OriginalResponse originalResponse = new OriginalResponse();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      originalResponse = (objectMapper.readValue(new File(FilesPath.ORIGINAL_INPUT_FILE), OriginalResponse.class));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return originalResponse;
  }

  public ArrayList<?> getOriginalDataByType(String constantOfDataType){
    String typeOfData = constantOfDataType;
    switch(typeOfData){
      case DataType.ORIGINAL_PERSON:
        logger.info("qsdfsdf");
        return getOriginalData().getPersons();

      case DataType.ORIGINAL_FIRESTATION:
        return getOriginalData().getFirestations();

      case DataType.ORIGINAL_MEDICALRECORD:
        return getOriginalData().getMedicalrecords();

      default:
        return null;
    }


  }
}
