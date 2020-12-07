package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataService;
import com.safetynet.alerts.interfaces.WorkingFirestationsService;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingFireStation;
import com.safetynet.alerts.model.WorkingResponse;
import com.safetynet.alerts.services.WorkingFileServiceImpl;
import com.safetynet.alerts.services.WorkingHomeServiceImpl;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateWorkingFileController<WorkingFireStationService, WorkingFireStationsService> {

  @Autowired
  WorkingFileServiceImpl workingFileServiceImpl;
  @Autowired
  RetrieveOriginalDataService retrieveOriginalDataService;
  @Autowired
  WorkingHomeService workingHomeServiceImpl;
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  WorkingPersonsService workingPersonsService;
  @Autowired
  WorkingFirestationsService workingFireStationService;

  @GetMapping("/createWorkingFile")
  public void createWorkingFile() {


    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      objectMapper.writeValue(new File(FilesPath.WORKING_INPUT_FILE), workingResponse);
    } catch (IOException e) {
      // logger.error("IOException", e);
    }


  }


}
