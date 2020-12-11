package com.safetynet.alerts.services;


import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.model.WorkingMedicalRecord;
import com.safetynet.alerts.repository.RetrieveOriginalDataRepositoryImpl;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFileServiceImpl {
  private static final Logger logger = LogManager.getLogger("App");

  RetrieveOriginalDataRepository retrieveOriginalData = new RetrieveOriginalDataRepositoryImpl();

  @Autowired
  RetrieveWorkingDataRepository retrieveOutPutDataRepository;


  public ArrayList<WorkingMedicalRecord> createWorkingMedicalRecords() {
    return null;
  }


}
