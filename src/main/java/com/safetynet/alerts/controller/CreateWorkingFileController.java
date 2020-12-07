package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.services.WorkingFileServiceImpl;
import com.safetynet.alerts.services.WorkingHomeServiceImpl;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateWorkingFileController<WorkingFireStationService, WorkingFireStationsService> {

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
  @Autowired
  WorkingMedicalRecordService workingMedicalRecordService;
  @Autowired
  OriginalPersonsService originalPersonsService;
  @Autowired
  OriginalMedicalRecordService originalMedicalRecordService;

  @GetMapping("/createWorkingFile")
  public void createWorkingFile() {

    HashMap<String, WorkingPerson> workingPersonsHashMap =
            workingPersonsService.getWorkingPersonsHashMap();
    HashMap<String, WorkingPerson> workingPersonsHashMapFinal = new HashMap<String,
            WorkingPerson>();

    HashMap<String, WorkingMedicalRecord> workingMedicalRecordHashMap =
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap();
    HashMap<String, OriginalMedicalrecords> originalMedicalRecordHashMap =
            originalMedicalRecordService.getOriginalMedicalRecordHashMap();

    HashMap<String, WorkingFireStation> workingFireStationHashMap =
            workingFireStationService.createWorkingFiresStationHashMap();
    HashMap<String, WorkingHome> workingHomeHashMap =
            workingHomeServiceImpl.getUnFinishedWorkingHomesHashMap();
    HashMap<UUID, OriginalPersons> identifiedPersonsHashMap = new HashMap<>();


    ArrayList<WorkingPerson> workingPersonsFinal = new ArrayList<WorkingPerson>();
    ArrayList<WorkingMedicalRecord> workingMedicalRecordsFinal = new ArrayList<>();
    ArrayList<WorkingFireStation> workingFireStationsFinal = new ArrayList<>();
    ArrayList<WorkingHome> workingHomesFinal = new ArrayList<>();

    for (Map.Entry<String, WorkingPerson> me : workingPersonsHashMap.entrySet()) {

      String currentKeyvalue = me.getKey();
      String[] currentKeyValueProcessed = me.getKey().split(",");
      String keyNames = currentKeyValueProcessed[0] + "," + currentKeyValueProcessed[1];
      String keyAdress =
              currentKeyValueProcessed[2] + "," + currentKeyValueProcessed[3] + "," + currentKeyValueProcessed[4];
      WorkingPerson workingPerson = new WorkingPerson();
      System.out.println(keyNames);
      UUID homeId = null;
      UUID medicalRecordId = null;
      LocalDate birthDate = null;
      System.out.println(workingHomeHashMap.keySet());
      if (workingHomeHashMap.containsKey(keyAdress)) {
        homeId = workingHomeHashMap.get(keyAdress).getIdHome();

      }
      if (workingMedicalRecordHashMap.containsKey(keyNames)) {
        medicalRecordId = workingMedicalRecordHashMap.get(keyNames).getIdMedicalRecord();

      }
      if (originalMedicalRecordHashMap.containsKey(keyNames)) {
        birthDate = originalMedicalRecordHashMap.get(keyNames).getBirthdate();
      }

      if (homeId != null && medicalRecordId != null && birthDate != null) {
        WorkingPerson currentPerson = me.getValue();
        currentPerson.setHomeID(homeId);
        currentPerson.setIdMedicalRecord(medicalRecordId);
        currentPerson.setBirthdate(birthDate);
        currentPerson.setAge(Period.between(birthDate, LocalDate.now()).getYears());
        workingPersonsHashMapFinal.put(currentKeyvalue, currentPerson);
      }
    }


    workingPersonsFinal.addAll(workingPersonsHashMap.values());


    WorkingResponse workingResponse = new WorkingResponse();
    workingResponse.setPersons(workingPersonsFinal);
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
