package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutMedicalRecordService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutMedicalRecord;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPutMedicalRecordServiceImpl implements OutPutMedicalRecordService {
  final Logger logger = LogManager.getLogger("OriginalFireStationServiceImpl");
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  /**
   * @inheritDoc
   */
  public OutPutMedicalRecord getMedicalRecordById(UUID medicalRecordId) {
    logger.debug("Entering getMedicalRecordById ");
    OutPutMedicalRecord medicalRecord = new OutPutMedicalRecord();
    ArrayList<OutPutMedicalRecord> outPutMedicalRecords =
            retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getMedicalrecords();

    for (OutPutMedicalRecord currentMedicalRecord : outPutMedicalRecords) {
      if (currentMedicalRecord.getIdMedicalRecord().equals(medicalRecordId)) {
        medicalRecord.setAllergies(currentMedicalRecord.getAllergies());
        medicalRecord.setMedications(currentMedicalRecord.getMedications());
        logger.debug("Success getMedicalRecordById ");
        return medicalRecord;
      }
    }
    logger.debug("Not match found getMedicalRecordById return null");
    return null;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutMedicalRecord> getAllMedicalRecords() {
    return retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getMedicalrecords();
  }
}
