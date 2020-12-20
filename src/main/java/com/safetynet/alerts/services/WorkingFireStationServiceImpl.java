package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.interfaces.WorkingFirestationsService;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingFireStation;
import com.safetynet.alerts.model.WorkingResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFireStationServiceImpl implements WorkingFirestationsService {

  final Logger logger = LogManager.getLogger("OriginalFireStationServiceImpl");
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  @Autowired
  RetrieveWorkingDataRepository retrieveWorkingDataRepository;

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<WorkingFireStation> reestablishCase(Collection<WorkingFireStation> workingFireStations) {
    logger.debug("Entering reestablishCase ");
    ArrayList<WorkingFireStation> result = new ArrayList<>();
    for (WorkingFireStation workingFireStation : workingFireStations) {
      WorkingFireStation processingFireStation = new WorkingFireStation();
      BeanUtils.copyProperties(workingFireStation, processingFireStation);
      result.add(processingFireStation);
    }
    logger.debug("Success reestablishCase ");
    return result;
  }

  /**
   * @inheritDoc
   */
  @Override
  public HashMap<Integer, WorkingFireStation> createWorkingFiresStationHashMap() {
    logger.debug("Entering createWorkingFiresStationHashMap ");
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
    logger.debug("Success createWorkingFiresStationHashMap " + fireStationsHashMap.toString());
    return fireStationsHashMap;
  }

  /**
   * @inheritDoc
   */
  @Override
  public HashMap<Integer, WorkingFireStation> getWorkingFireStationHashMap() {
    logger.debug("Entering getWorkingFireStationHashMap ");
    HashMap<Integer, WorkingFireStation> workingFireStationHashMap = new HashMap<>();
    WorkingResponse workingResponse =
            retrieveWorkingDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE);
    ArrayList<WorkingFireStation> workingFireStations = workingResponse.getFirestations();


    for (WorkingFireStation current : workingFireStations) {
      workingFireStationHashMap.put(current.getStationNumber(), current);
    }
    logger.debug("Success getWorkingFireStationHashMap ");
    return workingFireStationHashMap;
  }

}
