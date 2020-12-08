package com.safetynet.alerts.services;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.RetrieveOriginalDataRepositoryImpl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFileServiceImpl {
  private static final Logger logger = LogManager.getLogger("App");

  RetrieveOriginalDataRepository retrieveOriginalData = new RetrieveOriginalDataRepositoryImpl();

  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;


  public ArrayList<WorkingMedicalRecord> createWorkingMedicalRecords() {
    return null;
  }


}
