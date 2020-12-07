package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.WorkingFirestationsService;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.*;
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
  public HashMap<String, WorkingFireStation> createWorkingFiresStationHashMap() {
    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);

    HashMap<String, WorkingFireStation> fireStationsHashMap = new HashMap<String,
            WorkingFireStation>();
    for (OriginalFirestation originalFiresStation : originalResponse.getFirestations()) {
      WorkingFireStation workingFireStation = new WorkingFireStation();
      workingFireStation.setStationNumber(originalFiresStation.getStation());
      fireStationsHashMap.put(originalFiresStation.getAddress(), workingFireStation);

    }
    return fireStationsHashMap;
  }

}
