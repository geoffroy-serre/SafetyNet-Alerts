package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutResponse;

public interface RetrieveOutPutDataRepository {
  OutPutResponse getOutPutData(String constantFilePath);
}
