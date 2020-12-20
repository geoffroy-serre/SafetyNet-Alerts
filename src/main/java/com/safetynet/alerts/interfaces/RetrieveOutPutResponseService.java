package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutResponse;

public interface RetrieveOutPutResponseService {
  /**
   * Get data with output format
   *
   * @param filePath String
   * @return OutPutReponse
   */
  OutPutResponse retrieveOutPutResponse(String filePath);
}
