package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingResponse;

public interface RetrieveOutPutDataRepository {

  public WorkingResponse getWorkingData(String constantFilePath);
}
