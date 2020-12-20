package com.safetynet.alerts.interfaces;


import com.safetynet.alerts.model.WorkingMedicalRecord;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public interface WorkingMedicalRecordService {
  /**
   * Key: String "firstname,lastname,birthdate".
   * Value: WorkingMedicalRecord with id generated in it.
   *
   * @return HashMap<String, WorkingMedicalRecord>
   */
  HashMap<String, WorkingMedicalRecord> getWorkingMedicalRecordsHashMap();

  /**
   * from lower case to upper case for each first letter.
   *
   * @param workingMedicalRecords Collection<WorkingMedicalRecord>
   * @return ArrayList of WorkingMedicalRecord
   */
  ArrayList<WorkingMedicalRecord> reestablishCase(Collection<WorkingMedicalRecord> workingMedicalRecords);
}
