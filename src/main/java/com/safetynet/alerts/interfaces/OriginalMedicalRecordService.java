package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalMedicalrecord;
import java.util.ArrayList;
import java.util.HashMap;

public interface OriginalMedicalRecordService {
  /**
   * Add given OriginalMedicalRecord to the existing list
   * Verify if given MedicalRecord is allready present.
   *
   * @param originalMedicalrecord  OriginalMedicalrecord
   * @param originalMedicalrecords ArrayList<OriginalMedicalrecord>
   * @return ArrayList of OriginalMedicalRecord
   */
  ArrayList<OriginalMedicalrecord> postNewMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                        ArrayList<OriginalMedicalrecord> originalMedicalrecords);

  /**
   * Delete originalMedicalrecord from given list
   *
   * @param originalMedicalrecord  OriginalMedicalrecord
   * @param originalMedicalrecords ArrayList<OriginalMedicalrecord>
   * @return ArrayList<OriginalMedicalrecord>
   */
  ArrayList<OriginalMedicalrecord> deleteOriginalMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                               ArrayList<OriginalMedicalrecord> originalMedicalrecords);

  /**
   * Search for an OriginalMedicalrecord in given list.
   *
   * @param firstName      String
   * @param lastName       String
   * @param medicalrecords ArrayList<OriginalMedicalrecord>
   * @return boolean
   */
  boolean isMedicalRecordAlreadyInFile(String firstName, String lastName,
                                       ArrayList<OriginalMedicalrecord> medicalrecords
  );

  /**
   * Search for OriginalMedicalrecord in given list with given parameters.
   * Return new OriginalMedicalrecord if no matchses
   *
   * @param medicalRecords ArrayList<OriginalMedicalrecord>
   * @param firstName      String
   * @param lastName       String
   * @return OriginalMedicalrecord
   */
  OriginalMedicalrecord getMedicalRecordByFirstLastName(ArrayList<OriginalMedicalrecord> medicalRecords,
                                                        String firstName, String lastName);

  /**
   * Get OriginalMedicalRecords from original file.
   * key is String  firstName+ lastName.
   * value is OriginalMedicalRecord
   *
   * @return HashMap<String, OriginalMedicalRecord>
   */
  HashMap<String, OriginalMedicalrecord> getOriginalMedicalRecordHashMap();
}
