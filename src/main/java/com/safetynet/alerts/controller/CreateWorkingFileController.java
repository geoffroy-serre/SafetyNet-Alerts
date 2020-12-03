package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.model.WorkingResponse;
import com.safetynet.alerts.services.WorkingFileServiceImpl;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateWorkingFileController {

  @Autowired
  WorkingFileServiceImpl workingFileServiceImpl;

  @GetMapping("/createWorkingFile")
  public void createWorkingFile() {
    originalResponse = retrieveOriginalData.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    WorkingResponse workingResponse = new WorkingResponse();
    workingResponse.setHomes(createWorkingHomes(originalResponse));
    workingResponse.setPersons(createWorkingPersons());
    workingResponse.setFirestations(createWorkingFireStations());
    // workingResponse.setMedicalrecords(createWorkingMedicalRecords());

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      objectMapper.writeValue(new File(FilesPath.WORKING_INPUT_FILE), workingResponse);
    } catch (IOException e) {
      logger.error("IOException", e);
    }



  }
}
