package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;

public interface OriginalFleService {
  /**
   * Get data from file corresponding to OriginalResponse Mapping.
   *
   * @param constantOriginalDataFile String
   * @return OriginalResponse
   */
  OriginalResponse getOriginalResponse(String constantOriginalDataFile);

  /**
   * Write originalFile with provided OriginalResponse.
   *
   * @param originalResponse OriginalResponse
   */
  void writeOriginalFile(OriginalResponse originalResponse);
}
