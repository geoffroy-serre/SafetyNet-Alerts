package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutResponse;

public interface RetrieveOutPutDataRepository {
  /**
   * Get data with output format
   *
   * @param constantFilePath String
   * @return OutPutResponse
   */
  OutPutResponse getOutPutData(String constantFilePath);
}
