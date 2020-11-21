package com.safetynet.alerts.controller;



import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;
import com.safetynet.alerts.services.MedicalRecordService;
import com.safetynet.alerts.utils.WorkingFileOuput;

@RestController
public class MedicalRecordController {
  @Autowired
  MedicalRecordService medicalRecordService;
  private static final Logger logger = LogManager.getLogger("App");
  
  @GetMapping("/medicalRecordInfo")
  public MedicalRecordList getMedicalRecordController(){
    
    try {
      return medicalRecordService.getMedicalRecordsListService(WorkingFileOuput.getWorkingInputFile());
    } catch (JsonParseException e) {
      logger.info("Parse Error MedicalRecord ",e);
    } catch (JsonMappingException e) {
      logger.info("Mapping Error MedicalRecord ",e);
    } catch (NoSuchFileException e) {
      logger.info("No Such File Error MedicalRecord ",e);
    } catch (IOException e) {
      logger.info("IO error MedicalRecord ",e);
    }
    return null;
    
  }

  
  @PostMapping("/medicalRecord")
  public MedicalRecord postNewMedicalRecordController() {
   return medicalRecordService.postNewMedicalRecordService();   
  }
  
  @PutMapping("/medicalRecord")
  public MedicalRecord updateMedicalRecordController() {
   return medicalRecordService.updateMedicalRecordService();   
  }
  
  @DeleteMapping("/medicalRecord")
  public MedicalRecord deleteMedicalRecordController() {
   return medicalRecordService.deleteMedicalRecordService();   
  }
  

}
