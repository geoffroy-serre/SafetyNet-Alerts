package com.safetynet.alerts.controller;


import com.safetynet.alerts.Exceptions.AllreadyInDatabaseException;
import com.safetynet.alerts.Exceptions.NoDataInDataBaseException;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.CreateWorkingFileService;
import com.safetynet.alerts.interfaces.OriginalFleService;
import com.safetynet.alerts.interfaces.OriginalMedicalRecordService;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.PersonNames;
import com.safetynet.alerts.utils.RequestLogger;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
public class MedicalRecordController {

  @Autowired
  OriginalFleService originalFleService;
  @Autowired
  OriginalMedicalRecordService originalMedicalRecordService;
  @Autowired
  CreateWorkingFileService createWorkingFileService;
  private static final Logger logger = LogManager.getLogger("SafetyNetAlerts personController");

  @PostMapping("/medicalRecord")
  OriginalMedicalrecord postFireStation(@Valid @RequestBody OriginalMedicalrecord newMedicalRecord,
                                        final HttpServletResponse response,
                                        final HttpServletRequest request) {


    RequestLogger.logObjectRequest(request, "medicalRecordController");

    OriginalResponse originalResponse =
            originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE);

    ArrayList<OriginalMedicalrecord> originalMedicalRecords = originalResponse.getMedicalrecords();
    if (originalMedicalRecordService.isMedicalRecordAlreadyInFile(newMedicalRecord.getFirstName(),
            newMedicalRecord.getLastName(), originalMedicalRecords)) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " medical record already registered " + newMedicalRecord.toString());
      throw new AllreadyInDatabaseException("MedicalRecord known",
              newMedicalRecord.getFirstName() + " " + newMedicalRecord.getLastName());

    }

    originalMedicalRecordService.postNewMedicalRecord(newMedicalRecord, originalMedicalRecords);
    originalResponse.setMedicalrecords(originalMedicalRecords);
    originalFleService.writeOriginalFile(originalResponse);
    createWorkingFileService.createWorkingFile();
    if (response.getStatus() == 200 && !originalMedicalRecords.isEmpty()) {
      logger.info("Status : " + response.getStatus() + " added " + newMedicalRecord.toString());
    }
    return newMedicalRecord;

  }

  @PutMapping("/medicalRecord")
  OriginalMedicalrecord putFireStation(@Valid @RequestBody OriginalMedicalrecord modifyMedicalRecord,
                                       final HttpServletResponse response,
                                       final HttpServletRequest request) {


    RequestLogger.logObjectRequest(request, "fireStationController");

    OriginalResponse originalResponse =
            originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE);

    ArrayList<OriginalMedicalrecord> originalMedicalRecords = originalResponse.getMedicalrecords();

    if (!originalMedicalRecordService.isMedicalRecordAlreadyInFile(modifyMedicalRecord.getFirstName(),
            modifyMedicalRecord.getLastName(), originalMedicalRecords)) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " medical record already registered " + modifyMedicalRecord.toString());
      throw new NoDataInDataBaseException("MedicalRecord known",
              modifyMedicalRecord.getFirstName() + " " + modifyMedicalRecord.getLastName());

    }

    OriginalMedicalrecord originalMedicalRecord =
            originalMedicalRecordService.getMedicalRecordByFirstLastName(originalResponse.getMedicalrecords(), modifyMedicalRecord.getFirstName(), modifyMedicalRecord.getLastName());


    originalMedicalRecords = originalMedicalRecordService.deleteOriginalMedicalRecord(originalMedicalRecord,
            originalMedicalRecords);
    originalMedicalRecords.add(modifyMedicalRecord);
    originalResponse.setMedicalrecords(originalMedicalRecords);

    originalFleService.writeOriginalFile(originalResponse);
    createWorkingFileService.createWorkingFile();
    if (response.getStatus() == 200 && !originalMedicalRecords.isEmpty()) {
      logger.info("Status : " + response.getStatus() + modifyMedicalRecord.getFirstName()
              + " " + modifyMedicalRecord.getLastName() + " " + "modified " + modifyMedicalRecord.toString());
    }


    return modifyMedicalRecord;

  }

  @DeleteMapping(path="/medicalRecord", produces = "application/json")
  String deleteMedicalRecord(@Valid @RequestBody PersonNames deleteMedicalRecord,
                                       final HttpServletResponse response,
                                       final HttpServletRequest request) {


    RequestLogger.logObjectRequest(request, "fireStationController");

    OriginalResponse originalResponse =
            originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE);

    ArrayList<OriginalMedicalrecord> originalMedicalRecords = originalResponse.getMedicalrecords();

    if (!originalMedicalRecordService.isMedicalRecordAlreadyInFile(deleteMedicalRecord.getFirstName(),
            deleteMedicalRecord.getLastName(), originalMedicalRecords)) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " medical record already registered " + deleteMedicalRecord.toString());
      throw new NoDataInDataBaseException("MedicalRecord known",
              deleteMedicalRecord.getFirstName() + " " + deleteMedicalRecord.getLastName());

    }

    OriginalMedicalrecord originalMedicalRecord =
            originalMedicalRecordService.getMedicalRecordByFirstLastName(originalResponse.getMedicalrecords(), deleteMedicalRecord.getFirstName(), deleteMedicalRecord.getLastName());


    originalMedicalRecords = originalMedicalRecordService.deleteOriginalMedicalRecord(originalMedicalRecord,
            originalMedicalRecords);
    originalResponse.setMedicalrecords(originalMedicalRecords);

    originalFleService.writeOriginalFile(originalResponse);
    createWorkingFileService.createWorkingFile();
    if (response.getStatus() == 200 && !originalMedicalRecords.isEmpty()) {
      logger.info("Status : " + response.getStatus() + deleteMedicalRecord.getFirstName()
              + " " + deleteMedicalRecord.getLastName() + " " + "modified " + deleteMedicalRecord.toString());
    }

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("firstName: ",deleteMedicalRecord.getFirstName());
    jsonObject.put("lastName: ",deleteMedicalRecord.getLastName());
    jsonObject.put("medicalRecord: ",originalMedicalRecord.toString());


    return jsonObject.toString();

  }
}
