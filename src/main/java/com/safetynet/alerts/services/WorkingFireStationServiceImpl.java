package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.interfaces.WorkingFirestationsService;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.RetrieveWorkingDataRepositoryImpl;
import java.util.*;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFireStationServiceImpl implements WorkingFirestationsService {

  @Autowired
  OriginalResponse originalResponse;

  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;


  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<WorkingFireStation> reestablishCase(Collection<WorkingFireStation> workingFireStations){
    ArrayList<WorkingFireStation> result = new ArrayList<>();
    for (WorkingFireStation workingFireStation : workingFireStations){
      WorkingFireStation processingFireStation = new WorkingFireStation();
      BeanUtils.copyProperties(workingFireStation, processingFireStation);
      result.add(processingFireStation);
    }
    return result;
  }

  @Override
  /**
   * @inheritDoc
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
  /**
   * @inheritDoc
   */
  public HashMap<Integer, WorkingFireStation> getWorkingFireStationHashMap() {
    RetrieveWorkingDataRepository retrieveWorkingDataRepository =
            new RetrieveWorkingDataRepositoryImpl();
    HashMap<Integer, WorkingFireStation> workingFireStationHashMap = new HashMap<>();
    WorkingResponse  workingResponse =
            retrieveWorkingDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE);
    ArrayList<WorkingFireStation> workingFireStations = workingResponse.getFirestations();


    for (WorkingFireStation current : workingFireStations) {
      workingFireStationHashMap.put(current.getStationNumber(), current);
    }
    return workingFireStationHashMap;
  }

}
