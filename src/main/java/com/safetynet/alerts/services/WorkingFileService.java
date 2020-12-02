package com.safetynet.alerts.services;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalData;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.RetrieveOriginalDataImpl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFileService {
  private static final Logger logger = LogManager.getLogger("App");

  RetrieveOriginalData retrieveOriginalData = new RetrieveOriginalDataImpl();


  WorkingHomeService workingHomeService = new WorkingHomeService();

@Autowired
  OriginalResponse originalResponse;

  ArrayList<WorkingHome> workingHomes = new ArrayList<WorkingHome>();
  ArrayList<WorkingFireStation> workingFireStations = new ArrayList<WorkingFireStation>();


  public ArrayList<WorkingHome> createWorkingHomes(OriginalResponse originalResponse) {

    for (OriginalPersons currentIteration : originalResponse.getPersons()) {
      WorkingHome addingHome = new WorkingHome();
      if (workingHomeService.searchWorkingHome(currentIteration.getAddress(), workingHomes) == null){
        addingHome.setAddress(currentIteration.getAddress());
        addingHome.setCity(currentIteration.getCity());
        addingHome.setZip(currentIteration.getZip());
        addingHome.setIdHome(UUID.randomUUID());
        workingHomes.add(addingHome);
      }

    }
    return workingHomes;
  }

  /**
   * Return ID of FireStation if it is in workingFirestations.
   * Else return null
   *
   * @param stationNumber String
   * @return fireStationID UUID
   */
  public UUID presentFireStationId(String stationNumber) {

    UUID idFireStationPresent = null;
    for (WorkingFireStation currentFireStation : workingFireStations) {
      if (currentFireStation.getStationNumber().equals(stationNumber)) {
        idFireStationPresent = currentFireStation.getIdFireStation();
      }
    }
    return idFireStationPresent;
  }

  /**
   * Create List of Working FireStation with Original FireStation list
   * created by calling originalResponse.getFirestations();
   * @return ArrayList<WorkingFireStation>
   */
  public ArrayList<WorkingFireStation> createWorkingFireStations() {
    ArrayList<OriginalFirestation> originalFirestations = originalResponse.getFirestations();
    for (OriginalFirestation currentOriginalFirestation : originalFirestations) {
      WorkingFireStation workingFireStation = new WorkingFireStation();
      UUID alreadyPresentFireStationId =
              presentFireStationId(currentOriginalFirestation.getStation());

      if (alreadyPresentFireStationId != null) {
        for (WorkingFireStation presentFireStation : workingFireStations) {
          if (presentFireStation.getIdFireStation().equals(alreadyPresentFireStationId)) {
            presentFireStation.addWorkingHome(workingHomeService.searchWorkingHome(currentOriginalFirestation.getAddress(), workingHomes));
          }
        }
      } else {
        workingFireStation.setIdFireStation(UUID.randomUUID());
        workingFireStation.setStationNumber(currentOriginalFirestation.getStation());
        WorkingHome truc =
                workingHomeService.searchWorkingHome(currentOriginalFirestation.getAddress(), workingHomes);
        workingFireStation.addWorkingHome(truc);
        workingFireStations.add(workingFireStation);
      }

    }
    return workingFireStations;
  }

  public ArrayList<WorkingPerson> createWorkingPersons() {
    ArrayList<WorkingPerson> workingPersons = new ArrayList<WorkingPerson>();
    for (OriginalPersons originalPerson : originalResponse.getPersons()) {
      WorkingPerson workingPerson = new WorkingPerson();
      workingPerson.setId(UUID.randomUUID());
      workingPerson.setEmail(originalPerson.getEmail());
      workingPerson.setFirstName(originalPerson.getFirstName());
      workingPerson.setLastName(originalPerson.getLastName());

    }
    return null;
  }

  public ArrayList<WorkingMedicalRecord> createWorkingMedicalRecords() {
    return null;
  }

  public void createWorkingFile(String inputFile) {
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
      logger.error("IOException",e);
    }


  }
}
