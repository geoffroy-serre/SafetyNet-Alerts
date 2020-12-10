package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutResponse;

public interface RetrieveOutPutResponseService {
  OutPutResponse retrieveOutPutResponse(String filePath);
}
