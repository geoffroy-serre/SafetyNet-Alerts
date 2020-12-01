package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.DataType;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveData;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class MedicalRecordService.
 */
@Service
public class MedicalRecordService {

  @Autowired
  RetrieveData retrieveData;

  public ArrayList<?> getMedicalRecordService() {
    return retrieveData.getDataByType(FilesPath.ORIGINAL_INPUT_FILE,
            DataType.ORIGINAL_MEDICALRECORD);
  }
}
