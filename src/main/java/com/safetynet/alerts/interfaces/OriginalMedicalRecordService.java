package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalMedicalrecords;
import java.util.HashMap;

public interface OriginalMedicalRecordService {

  HashMap<String, OriginalMedicalrecords> getOriginalMedicalRecordHashMap();
}
