package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFileService {
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalData;

  public OriginalResponse getOriginalResponse(String constantOriginalDataFile){
    return retrieveOriginalData.getOriginalData(constantOriginalDataFile);
  }
}
