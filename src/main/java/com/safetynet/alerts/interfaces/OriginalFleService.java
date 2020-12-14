package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;

public interface OriginalFleService {
  OriginalResponse getOriginalResponse(String constantOriginalDataFile);

  void writeOriginalFile(OriginalResponse originalResponse);
}
