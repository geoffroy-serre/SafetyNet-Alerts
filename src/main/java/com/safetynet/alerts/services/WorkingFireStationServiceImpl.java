package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.interfaces.WorkingFirestationsService;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.RetrieveOutPutDataRepositoryImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFireStationServiceImpl implements WorkingFirestationsService {

  @Autowired
  OriginalResponse originalResponse;

  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;




  /**
   * Retrieve Original FiresStation for processing.
   * Return HashMpa with key as address, and value is WorkingFirestation
   *
   * @return HashMap<String, WorkingFireStation>
   */
  public HashMap<Integer, WorkingFireStation> createWorkingFiresStationHashMap() {
    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);

    HashMap<Integer, WorkingFireStation> fireStationsHashMap = new HashMap<Integer,
            WorkingFireStation>();
    for (OriginalFirestation originalFiresStation : originalResponse.getFirestations()) {
      WorkingFireStation workingFireStation = new WorkingFireStation();
      workingFireStation.setStationNumber(originalFiresStation.getStation());
      workingFireStation.setIdFireStation(UUID.randomUUID());
      fireStationsHashMap.put(workingFireStation.getStationNumber(), workingFireStation);

    }
    return fireStationsHashMap;
  }

  @Override
  public HashMap<Integer, WorkingFireStation> getWorkingFireStationHashMap() {
    RetrieveOutPutDataRepository retrieveOutPutDataRepository = new RetrieveOutPutDataRepositoryImpl();
    HashMap<Integer, WorkingFireStation> workingFireStationHashMap = new HashMap<>();
    WorkingResponse  workingResponse =
            retrieveOutPutDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE);
    ArrayList<WorkingFireStation> workingFireStations = workingResponse.getFirestations();

    for (WorkingFireStation current : workingFireStations) {
      workingFireStationHashMap.put(current.getStationNumber(), current);
    }
    return workingFireStationHashMap;
  }

}
