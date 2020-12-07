package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalFireStationService;
import com.safetynet.alerts.interfaces.OriginalPersonsService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalPersons;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFireStationServiceImpl implements OriginalFireStationService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;


  @Override
  public HashMap<Integer, OriginalFirestation> getOriginalFireStationHashMap() {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<Integer, OriginalFirestation> originalFireStationHashMap = new HashMap<>();

    for (OriginalFirestation originalFireStation : originalResponse.getFirestations()) {
      originalFireStationHashMap.put(originalFireStation.getStation(),
              originalFireStation);
    }
    return originalFireStationHashMap;
  }


}
