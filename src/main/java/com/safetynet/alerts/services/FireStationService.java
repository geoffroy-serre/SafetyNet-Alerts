package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.DataType;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveData;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FireStationService {

  @Autowired
  RetrieveData retrieveData;

  public ArrayList<?> getOriginalFireStations() {
    return retrieveData.getDataByType(FilesPath.ORIGINAL_INPUT_FILE,
            DataType.ORIGINAL_FIRESTATION);
  }
}

