package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.interfaces.RetrieveOutPutResponseService;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveOutPutResponseServiceImpl implements RetrieveOutPutResponseService {
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;


  @Override
  /**
   * @inheritDoc
   */
  public OutPutResponse retrieveOutPutResponse(String filePath) {
    OutPutResponse result = retrieveOutPutDataRepository.getOutPutData(filePath);

  return result;
  }

}
