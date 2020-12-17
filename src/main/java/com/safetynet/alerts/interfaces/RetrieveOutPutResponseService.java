package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutResponse;

public interface RetrieveOutPutResponseService {
  /**
   * @param filePath
   * @return OutPutReponse
   */
  OutPutResponse retrieveOutPutResponse(String filePath);
}
