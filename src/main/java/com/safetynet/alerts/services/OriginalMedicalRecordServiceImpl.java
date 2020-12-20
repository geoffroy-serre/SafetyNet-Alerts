package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalMedicalRecordService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalMedicalRecordServiceImpl implements OriginalMedicalRecordService {
  final Logger logger = LogManager.getLogger("OriginalMedicalRecordServiceImpl");
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalMedicalrecord> postNewMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                               ArrayList<OriginalMedicalrecord> originalMedicalrecords) {
    logger.debug("Entering postNewMedicalRecord ");
    boolean isPresent = false;
    for (OriginalMedicalrecord currentMedicalRecord : originalMedicalrecords) {
      if (isMedicalRecordAlreadyInFile(originalMedicalrecord.getFirstName()
              , originalMedicalrecord.getLastName(), originalMedicalrecords)) {
        isPresent = true;
      }
    }
    if (isPresent) {
      logger.debug("postNewMedicalRecord not match return empty list ");
      return originalMedicalrecords;
    }
    ArrayList<OriginalMedicalrecord> originalMedicalRecordResult = originalMedicalrecords;
    originalMedicalRecordResult.add(originalMedicalrecord);
    logger.debug("Success postNewMedicalRecord ");
    return originalMedicalRecordResult;
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalMedicalrecord> deleteOriginalMedicalRecord(OriginalMedicalrecord originalMedicalrecord,
                                                                      ArrayList<OriginalMedicalrecord> originalMedicalrecords) {
    logger.debug("Entering deleteOriginalMedicalRecord ");
    ArrayList<OriginalMedicalrecord> results = new ArrayList<>();
    for (OriginalMedicalrecord currentMedicalrecord : originalMedicalrecords) {
      if (!originalMedicalrecord.getFirstName().equalsIgnoreCase(currentMedicalrecord.getFirstName()) &&
              !originalMedicalrecord.getLastName().equalsIgnoreCase(currentMedicalrecord.getLastName())) {

        results.add(currentMedicalrecord);
      }
    }
    logger.debug("Success deleteOriginalMedicalRecord ");
    return results;
  }

  /**
   * @inheritDoc
   */
  @Override
  public boolean isMedicalRecordAlreadyInFile(String firstName, String lastName,
                                              ArrayList<OriginalMedicalrecord> medicalrecords
  ) {
    logger.debug("Entering isMedicalRecordAlreadyInFile ");
    boolean isAlreadyInFile = false;
    OriginalMedicalrecord selectedMedicalrecords =
            getMedicalRecordByFirstLastName(medicalrecords, firstName, lastName);
    if (selectedMedicalrecords.getFirstName() != null && selectedMedicalrecords.getLastName() != null) {
      isAlreadyInFile = true;
    }
    logger.debug("Success isMedicalRecordAlreadyInFile ");
    return isAlreadyInFile;
  }

  /**
   * @inheritDoc
   */
  @Override
  public OriginalMedicalrecord getMedicalRecordByFirstLastName(ArrayList<OriginalMedicalrecord> medicalRecords,
                                                               String firstName, String lastName) {
    logger.debug("Entering getMedicalRecordByFirstLastName ");
    for (OriginalMedicalrecord originalMedicalrecord : medicalRecords) {
      if (originalMedicalrecord.getFirstName().equalsIgnoreCase(firstName) && originalMedicalrecord.getLastName().equalsIgnoreCase(lastName)) {
        logger.debug("Success getMedicalRecordByFirstLastName ");
        return originalMedicalrecord;
      }
    }
    logger.debug("Not match Found getMedicalRecordByFirstLastName  returning null");
    return new OriginalMedicalrecord();
  }

  /**
   * @inheritDoc
   */
  @Override
  public HashMap<String, OriginalMedicalrecord> getOriginalMedicalRecordHashMap() {
    logger.debug("Entering getOriginalMedicalRecordHashMap ");
    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, OriginalMedicalrecord> originalMedicalRecordHashMap = new HashMap<>();

    for (OriginalMedicalrecord originalMedicalRecords : originalResponse.getMedicalrecords()) {
      originalMedicalRecordHashMap.put(originalMedicalRecords.getFirstName() + "," + originalMedicalRecords.getLastName(),
              originalMedicalRecords);
    }
    logger.debug("Success getOriginalMedicalRecordHashMap ");
    return originalMedicalRecordHashMap;
  }


}
