package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.interfaces.RetrieveOutPutResponseService;
import com.safetynet.alerts.model.OutPutResponse;
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
