package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;

public interface RetrieveOriginalDataService {
   OriginalResponse retrieveOriginalData(String filepath);
}
