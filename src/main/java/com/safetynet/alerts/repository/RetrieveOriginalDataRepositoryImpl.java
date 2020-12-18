package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
public class RetrieveOriginalDataRepositoryImpl implements RetrieveOriginalDataRepository {
  private static final Logger logger = LogManager.getLogger("App");


  @Override
  /**
   * @inheritDoc
   */
  public OriginalResponse getOriginalData(String constantFilePath) {
    String filePath = constantFilePath;
    ObjectMapper objectMapper = new ObjectMapper();
    OriginalResponse response = new OriginalResponse();

    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      logger.debug("Get Original Response from RetrieveOriginalDataRepositoryImpl");
      response = (objectMapper.readValue(new File(filePath),
              OriginalResponse.class));
    } catch (IOException e) {
      logger.error("IOException getting Original Data ", e);
      return new OriginalResponse();
    }
    logger.debug("return OriginalResponse for getOriginalData ");
    if(response == null){
      return new OriginalResponse();
    }
    OriginalResponse originalResponse = response;

    HashSet<OriginalPerson> originalPersonHashSet = new HashSet<>();
    HashSet<OriginalFirestation> originalFirestationHashet = new HashSet<>();
    HashSet<OriginalMedicalrecord> originalMedicalrecordHashSet = new HashSet<>();
    OriginalResponse result = new OriginalResponse();

    for (OriginalPerson originalPerson : originalResponse.getPersons()) {
      OriginalPerson personProcessed = new OriginalPerson();
      BeanUtils.copyProperties(originalPerson, personProcessed);
      personProcessed.setAddress(originalPerson.getAddress().toLowerCase());
      personProcessed.setFirstName(originalPerson.getFirstName().toLowerCase());
      personProcessed.setLastName(originalPerson.getLastName().toLowerCase());
      personProcessed.setCity(originalPerson.getCity().toLowerCase());
      personProcessed.setZip(originalPerson.getZip().toLowerCase());
      originalPersonHashSet.add(personProcessed);
    }
    for (OriginalFirestation originalFirestation : originalResponse.getFirestations()) {
      OriginalFirestation processedFirestation = new OriginalFirestation();
      BeanUtils.copyProperties(originalFirestation, processedFirestation);
      processedFirestation.setAddress(originalFirestation.getAddress().toLowerCase());
      originalFirestationHashet.add(processedFirestation);
    }

    for (OriginalMedicalrecord originalMedicalrecord : originalResponse.getMedicalrecords()) {
      OriginalMedicalrecord processedMedicalrecord = new OriginalMedicalrecord();
      BeanUtils.copyProperties(originalMedicalrecord, processedMedicalrecord);
      processedMedicalrecord.setFirstName(originalMedicalrecord.getFirstName().toLowerCase());
      processedMedicalrecord.setLastName((originalMedicalrecord.getLastName().toLowerCase()));

      originalMedicalrecordHashSet.add(processedMedicalrecord);

    }

    ArrayList<OriginalPerson> originalPersons = new ArrayList<>(originalPersonHashSet);
    ArrayList<OriginalFirestation> originalFirestations =
            new ArrayList<>(originalFirestationHashet);
    ArrayList<OriginalMedicalrecord> originalMedicalrecords =
            new ArrayList<>(originalMedicalrecordHashSet);

    result.setFirestations(originalFirestations);
    result.setPersons(originalPersons);
    result.setMedicalrecords(originalMedicalrecords);
    return result;
  }


}
