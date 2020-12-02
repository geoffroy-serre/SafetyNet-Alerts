package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingResponse;
import java.util.ArrayList;


public interface RetrieveOriginalData {

  OriginalResponse getOriginalData(String constantFilePath);

}
