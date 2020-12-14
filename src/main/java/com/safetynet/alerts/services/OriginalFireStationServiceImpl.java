package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalFireStationService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFireStationServiceImpl implements OriginalFireStationService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;


  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OriginalFirestation> getOriginalFireStations() {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    ArrayList<OriginalFirestation> originalFireStations = new ArrayList<>();

    for (OriginalFirestation originalFireStation : originalResponse.getFirestations()) {
      originalFireStations.add(originalFireStation);
    }
    return originalFireStations;
  }


}
