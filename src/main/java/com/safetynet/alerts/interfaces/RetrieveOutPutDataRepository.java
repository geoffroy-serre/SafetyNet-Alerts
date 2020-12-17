package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutResponse;

public interface RetrieveOutPutDataRepository {
  /**
   * @param constantFilePath
   * @return OutPutResponse
   */
  OutPutResponse getOutPutData(String constantFilePath);
}
