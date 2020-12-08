package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.services.OriginalFileService;
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
  @Autowired
  OriginalFireStationService originalFireStationService;

  @GetMapping("/createWorkingFile")
  public void createWorkingFile() {


    HashMap<String, WorkingHome> workingHomeHashMap =
            workingHomeServiceImpl.getUnFinishedWorkingHomesHashMap();

    HashMap<String, WorkingMedicalRecord> workingMedicalRecordHashMap =
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap();

    HashMap<String, OriginalMedicalrecords> originalMedicalRecordHashMap =
            originalMedicalRecordService.getOriginalMedicalRecordHashMap();

    ArrayList<WorkingPerson> workingPersonsFinal = new ArrayList<WorkingPerson>();
    ArrayList<WorkingMedicalRecord> workingMedicalRecordsFinal = new ArrayList<>();
    ArrayList<WorkingFireStation> workingFireStationsFinal = new ArrayList<>();
    ArrayList<WorkingHome> workingHomesFinal = new ArrayList<>();

    /*
    Linking Persons to home and medical records id
     */
    HashMap<String, WorkingPerson> workingPersonsHashMap =
            workingPersonsService.getWorkingPersonsHashMap();
    HashMap<String, WorkingPerson> workingPersonsHashMapFinal = new HashMap<String,
            WorkingPerson>();
    for (Map.Entry<String, WorkingPerson> me : workingPersonsHashMap.entrySet()) {
      String currentKeyvalue = me.getKey();
      String[] currentKeyValueProcessed = me.getKey().split(",");
      String keyNames = currentKeyValueProcessed[0] + "," + currentKeyValueProcessed[1];
      String keyAddress = currentKeyValueProcessed[2];
      UUID homeId = null;
      UUID medicalRecordId = null;
      LocalDate birthDate = null;
      WorkingHome currentHome = new WorkingHome();

      if (workingHomeHashMap.containsKey(keyAddress)) {
        homeId = workingHomeHashMap.get(keyAddress).getIdHome();
        currentHome = workingHomeHashMap.get(keyAddress);
      }

      if (workingMedicalRecordHashMap.containsKey(keyNames)) {
        medicalRecordId = workingMedicalRecordHashMap.get(keyNames).getIdMedicalRecord();
      }
      if (originalMedicalRecordHashMap.containsKey(keyNames)) {
        birthDate = originalMedicalRecordHashMap.get(keyNames).getBirthdate();
      }
      if (homeId != null && medicalRecordId != null && birthDate != null) {
        WorkingPerson workingPerson = new WorkingPerson();
        WorkingPerson currentPerson = me.getValue();
        currentPerson.setHomeID(homeId);
        currentPerson.setIdMedicalRecord(medicalRecordId);
        currentPerson.setBirthdate(birthDate);
        currentPerson.setAge(Period.between(birthDate, LocalDate.now()).getYears());
        workingPersonsHashMapFinal.put(currentKeyvalue, currentPerson);
        currentHome.addPerson(currentPerson);
        workingHomeHashMap.put(keyAddress, currentHome);

      }
    }

    /*
    Populating Home list of Firestations
     */
    ArrayList<OriginalFirestation> originalFirestations =
            originalFireStationService.getOriginalFireStations();
    HashMap<Integer, WorkingFireStation> workingFireStationHashMap =
            workingFireStationService.createWorkingFiresStationHashMap();

    for (OriginalFirestation currentOriginalFirestation : originalFirestations) {
      WorkingHome addHome = new WorkingHome();
      WorkingFireStation addFireStation = new WorkingFireStation();
      String keyAddress = currentOriginalFirestation.getAddress();
      if (workingHomeHashMap.containsKey(keyAddress)) {
        addHome = workingHomeHashMap.get(keyAddress);
      }
      if (workingFireStationHashMap.containsKey(currentOriginalFirestation.getStation())) {
        addFireStation = workingFireStationHashMap.get(currentOriginalFirestation.getStation());
      }
      addFireStation.addWorkingHome(addHome);
      workingFireStationHashMap.put(currentOriginalFirestation.getStation(), addFireStation);
    }


    /*
    Adding list to be mapped
     */
    workingMedicalRecordsFinal.addAll(workingMedicalRecordHashMap.values());
    workingPersonsFinal.addAll(workingPersonsHashMap.values());
    workingFireStationsFinal.addAll(workingFireStationHashMap.values());
    workingHomesFinal.addAll(workingHomeHashMap.values());

    WorkingResponse workingResponse = new WorkingResponse();
    workingResponse.setPersons(workingPersonsFinal);
    workingResponse.setHomes(workingHomesFinal);
    workingResponse.setFirestations(workingFireStationsFinal);
    workingResponse.setMedicalrecords(workingMedicalRecordsFinal);
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
