package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingResponse;

public interface RetrieveWorkingDataRepository {
  /**
   * Get data with Working Format.
   *
   * @param constantFilePath String
   * @return WorkingResponse
   */
  public WorkingResponse getWorkingData(String constantFilePath);
}
