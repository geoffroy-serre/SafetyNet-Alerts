package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalMedicalRecordService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
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
