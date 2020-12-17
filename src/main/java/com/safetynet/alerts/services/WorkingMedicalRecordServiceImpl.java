package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.WorkingMedicalRecordService;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingMedicalRecord;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingMedicalRecordServiceImpl implements WorkingMedicalRecordService {

  final Logger logger = LogManager.getLogger("WorkingMedicalRecordServiceImpl");
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @Override
  /**
   * @inheritDoc
   */
  public HashMap<String, WorkingMedicalRecord> getWorkingMedicalRecordsHashMap() {
    logger.debug("Entering getWorkingMedicalRecordsHashMap ");
    OriginalResponse originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, WorkingMedicalRecord> workingMedicalRecordHashMap = new HashMap<>();
    for (OriginalMedicalrecord originalMedicalrecords : originalResponse.getMedicalrecords()) {
      WorkingMedicalRecord workingMedicalRecord = new WorkingMedicalRecord();
      workingMedicalRecord.setAllergies(originalMedicalrecords.getAllergies());
      workingMedicalRecord.setMedications(originalMedicalrecords.getMedications());
      workingMedicalRecord.setIdMedicalRecord(UUID.randomUUID());
      workingMedicalRecordHashMap.put(originalMedicalrecords.getFirstName() + ","
              + originalMedicalrecords.getLastName(), workingMedicalRecord);
    }
    logger.debug("Success getWorkingMedicalRecordsHashMap ");
    return workingMedicalRecordHashMap;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<WorkingMedicalRecord> reestablishCase(Collection<WorkingMedicalRecord> workingMedicalRecords) {
    logger.debug("Entering reestablishCase ");
    ArrayList<WorkingMedicalRecord> result = new ArrayList<>();
    for (WorkingMedicalRecord workingMedicalRecord : workingMedicalRecords) {
      WorkingMedicalRecord processingMedicalRecord = new WorkingMedicalRecord();
      BeanUtils.copyProperties(workingMedicalRecord, processingMedicalRecord);
      result.add(processingMedicalRecord);
    }
    logger.debug("Success reestablishCase ");
    return result;
  }

}
