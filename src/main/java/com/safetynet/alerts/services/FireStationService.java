package com.safetynet.alerts.services;

import java.util.ArrayList;

import com.safetynet.alerts.constants.DataType;
import com.safetynet.alerts.interfaces.RetrieveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FireStationService {

  @Autowired
  RetrieveData retrieveData;

  public ArrayList<?> getOriginalFireStations(){
    return retrieveData.getOriginalDataByType(DataType.ORIGINAL_FIRESTATION);
  }
}

