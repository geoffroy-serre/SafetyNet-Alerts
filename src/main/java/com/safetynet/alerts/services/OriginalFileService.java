package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.RetrieveOriginalData;
import com.safetynet.alerts.model.OriginalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFileService {
  @Autowired
  RetrieveOriginalData retrieveOriginalData;

  public OriginalResponse getOriginalResponse(String constantOriginalDataFile){
    return retrieveOriginalData.getOriginalData(constantOriginalDataFile);
  }
}
