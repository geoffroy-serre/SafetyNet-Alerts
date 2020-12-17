package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;

public interface RetrieveOriginalDataService {
  /**
   * HashSet used to check for duplicates
   *
   * @param filepath
   * @return OriginalResponse
   */
  OriginalResponse retrieveOriginalData(String filepath);
}
