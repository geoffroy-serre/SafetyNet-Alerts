package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutMedicalRecord;
import java.util.UUID;

public interface OutPutMedicalRecordService {
  OutPutMedicalRecord getMedicalRecordById(UUID medicalRecordId);
}
