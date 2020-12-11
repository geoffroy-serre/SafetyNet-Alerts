package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;


public interface RetrieveOriginalDataRepository {

  OriginalResponse getOriginalData(String constantFilePath);

}
