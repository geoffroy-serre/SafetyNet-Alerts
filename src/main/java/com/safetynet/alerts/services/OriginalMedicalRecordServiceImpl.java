package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalMedicalRecordService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.OutPutFireStation;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalMedicalRecordServiceImpl implements OriginalMedicalRecordService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OriginalMedicalrecord> postNewMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                               ArrayList<OriginalMedicalrecord> originalMedicalrecords) {
    boolean isPresent = false;
    for (OriginalMedicalrecord currentMedicalRecord : originalMedicalrecords) {
      if (isMedicalRecordAlreadyInFile(originalMedicalrecord.getFirstName()
              , originalMedicalrecord.getLastName(), originalMedicalrecords)) {
        isPresent = true;
      }
    }
    if (isPresent) {
      return new ArrayList<OriginalMedicalrecord>();
    }
    ArrayList<OriginalMedicalrecord> originalMedicalRecordResult = originalMedicalrecords;
    originalMedicalRecordResult.add(originalMedicalrecord);
    return originalMedicalRecordResult;
  }

@Override
/**
 * @inheritDoc
 */
  public ArrayList<OriginalMedicalrecord> deleteOriginalMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                                  ArrayList<OriginalMedicalrecord> originalMedicalrecords) {
    ArrayList<OriginalMedicalrecord> results = new ArrayList<>();
    for (OriginalMedicalrecord currentMedicalrecord : originalMedicalrecords) {
      if (!originalMedicalrecord.getFirstName().equalsIgnoreCase(currentMedicalrecord.getFirstName()) &&
        !originalMedicalrecord.getLastName().equalsIgnoreCase(currentMedicalrecord.getLastName())){

        results.add(currentMedicalrecord);
      }
    }
    return results;
  }


  @Override
  /**
   * @inheritDoc
   */
  public boolean isMedicalRecordAlreadyInFile(String firstName, String lastName,
                                              ArrayList<OriginalMedicalrecord> medicalrecords
  ) {
    boolean isAlreadyInFile = false;
   OriginalMedicalrecord selectedMedicalrecords =
            getMedicalRecordByFirstLastName(medicalrecords,firstName,lastName);
    if (selectedMedicalrecords != null) {
      isAlreadyInFile = true;
    }
    return isAlreadyInFile;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OriginalMedicalrecord getMedicalRecordByFirstLastName(ArrayList<OriginalMedicalrecord> medicalRecords,
                                                               String firstName, String lastName) {
    for (OriginalMedicalrecord originalMedicalrecord : medicalRecords) {
      if (originalMedicalrecord.getFirstName().equalsIgnoreCase(firstName) && originalMedicalrecord.getLastName().equalsIgnoreCase(lastName)) {
        return originalMedicalrecord;
      }
    }
    return null;
  }

  @Override
  /**
   * @inheritDoc
   */
  public HashMap<String, OriginalMedicalrecord> getOriginalMedicalRecordHashMap() {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, OriginalMedicalrecord> originalMedicalRecordHashMap = new HashMap<>();

    for (OriginalMedicalrecord originalMedicalRecords : originalResponse.getMedicalrecords()) {
      originalMedicalRecordHashMap.put(originalMedicalRecords.getFirstName()+","+originalMedicalRecords.getLastName(),
              originalMedicalRecords);
    }
    return originalMedicalRecordHashMap;
  }




}
