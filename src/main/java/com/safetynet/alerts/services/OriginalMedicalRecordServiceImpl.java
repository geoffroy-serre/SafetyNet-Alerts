package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalMedicalRecordService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalMedicalrecords;
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
  public HashMap<String, OriginalMedicalrecords> getOriginalMedicalRecordHashMap() {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, OriginalMedicalrecords> originalMedicalRecordHashMap = new HashMap<>();

    for (OriginalMedicalrecords originalMedicalRecords : originalResponse.getMedicalrecords()) {
      originalMedicalRecordHashMap.put(originalMedicalRecords.getFirstName()+","+originalMedicalRecords.getLastName(),
              originalMedicalRecords);
    }
    return originalMedicalRecordHashMap;
  }


}
