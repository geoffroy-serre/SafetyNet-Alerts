package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalMedicalrecord;
import java.util.ArrayList;
import java.util.HashMap;

public interface OriginalMedicalRecordService {
  ArrayList<OriginalMedicalrecord> postNewMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                        ArrayList<OriginalMedicalrecord> originalMedicalrecords);

  ArrayList<OriginalMedicalrecord> deleteOriginalMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                               ArrayList<OriginalMedicalrecord> originalMedicalrecords);

  boolean isMedicalRecordAlreadyInFile(String firstName, String lastName,
                                       ArrayList<OriginalMedicalrecord> medicalrecords
  );

  OriginalMedicalrecord getMedicalRecordByFirstLastName(ArrayList<OriginalMedicalrecord> medicalRecords,
                                                        String firstName, String lastName);

  /**
   * Get OriginalMedicalRecords from original file.
   * key is String  firstName+ lastName.
   * value is OriginalMedicalRecord
   * @return HashMap<String, OriginalMedicalRecord>
   */
  HashMap<String, OriginalMedicalrecord> getOriginalMedicalRecordHashMap();
}
