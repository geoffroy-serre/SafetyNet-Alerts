package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataService;
import com.safetynet.alerts.model.OriginalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveOriginalDataServiceImpl implements RetrieveOriginalDataService {
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @Override
  public OriginalResponse retrieveOriginalData(String filepath) {
    return retrieveOriginalDataRepository.getOriginalData(filepath);

  }
}
