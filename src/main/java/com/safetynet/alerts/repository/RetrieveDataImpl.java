package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.DataType;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.Response;
import com.safetynet.alerts.interfaces.RetrieveData;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class RetrieveDataImpl implements RetrieveData {
  private static final Logger logger = LogManager.getLogger("App");

  /**
   * Auto use Working or Original Objects Files depending on constant FIle äth used.
   * Return corresponding Response Object.
   *
   * @param constantFilePath input file.
   * @return Response
   */
  @Override
  public Response getData(String constantFilePath) {
    String filePath = constantFilePath;
    ObjectMapper objectMapper = new ObjectMapper();
    Response response = new OriginalResponse();

    if (filePath.equals(FilesPath.ORIGINAL_INPUT_FILE)) {
      response = new OriginalResponse();
    }
    if (filePath.equals(FilesPath.WORKING_INPUT_FILE)) {
      response = new WorkingResponse();
    }
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      response = (objectMapper.readValue(new File(filePath),
              response.getClass()));
    } catch (IOException e) {
      logger.error("IOException ", e);
    }

    return response;
  }

  /**
   * Use DataType accordingly of the type of object you want to process.
   * Use DataType shortOne
   * Return empty list if DataType is unknown
   *
   * @param constantOfDataType from DataType Constants
   * @return ArrayList
   */
  public ArrayList<?> getDataByType(String constantFilePath, String constantOfDataType) {
    String typeOfData = constantOfDataType;
    String filePath = constantFilePath;

    switch (typeOfData) {
      case DataType.PERSON:
        return getData(filePath).getPersons();

      case DataType.FIRESTATION:
        return getData(filePath).getFirestations();

      case DataType.MEDICALRECORD:
        return getData(filePath).getMedicalrecords();

      default:
        logger.info("No or wrong type of data choosen");
        return null;

    }
  }


}



