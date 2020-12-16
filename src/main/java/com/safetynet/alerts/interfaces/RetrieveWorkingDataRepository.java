package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingResponse;

public interface RetrieveWorkingDataRepository {
  /**
   *
   * @param constantFilePath
   * @return WorkingResponse
   */
  public WorkingResponse getWorkingData(String constantFilePath);
}
