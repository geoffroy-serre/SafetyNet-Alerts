package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;


public interface RetrieveOriginalDataRepository {
  /**
   * HashSet used to check for duplicates.
   *
   * @param constantFilePath String
   * @return OriginalResponse
   */
  OriginalResponse getOriginalData(String constantFilePath);

}
