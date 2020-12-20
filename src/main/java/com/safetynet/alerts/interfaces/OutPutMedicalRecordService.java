package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutMedicalRecord;
import java.util.ArrayList;
import java.util.UUID;

public interface OutPutMedicalRecordService {
  /**
   * Return null if no match.
   *
   * @param medicalRecordId
   * @return OutPutMedicalRecord
   */
  OutPutMedicalRecord getMedicalRecordById(UUID medicalRecordId);

  /**
   * getAllMedicalRecords.
   *
   * @return ArrayList of OutPutMedicalRecord from working file.
   */
  ArrayList<OutPutMedicalRecord> getAllMedicalRecords();
}
