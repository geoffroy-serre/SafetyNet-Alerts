package com.safetynet.alerts.interfaces;

import java.util.ArrayList;

import com.safetynet.alerts.model.OriginalResponse;

public interface RetrieveData {

  public OriginalResponse getOriginalData();
  public ArrayList<?> getOriginalDataByType(String constantOfDataType);
}
