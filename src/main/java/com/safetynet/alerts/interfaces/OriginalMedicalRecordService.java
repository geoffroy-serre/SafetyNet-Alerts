package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalMedicalrecord;
import java.util.HashMap;

public interface OriginalMedicalRecordService {
  /**
   * Get OriginalMedicalRecords from original file.
   * key is String  firstName+ lastName.
   * value is OriginalMedicalRecord
   * @return HashMap<String, OriginalMedicalRecord>
   */
  HashMap<String, OriginalMedicalrecord> getOriginalMedicalRecordHashMap();
}
