package com.safetynet.alerts.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.dao.PersonDaoImpl;
import com.safetynet.alerts.interfaces.IFireStationDao;
import com.safetynet.alerts.interfaces.IMedicalRecordDao;
import com.safetynet.alerts.interfaces.IPersonDao;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.HomeList;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.utils.WorkingFileOuput;

@Component
public class CreateWorkingFileService {
 
  
  PersonList personList;
 
  FireStationList fireStationList;
  
  
  HomeList homeList = new HomeList();
  
  MedicalRecordList medicalRecordList;
  @Autowired
  IPersonDao ipersonDao;
  @Autowired
  IFireStationDao iFirestationDao; 
  @Autowired
  IMedicalRecordDao imedicalRecordDao;
  
  
  Home home = new Home();;

  private static final Logger logger = LogManager.getLogger("App");

  
  public void createPersonListAndIDFromOriginalFile() {
    try {
      personList = ipersonDao.getPersonListDao();
      logger.info("list getted");
    } catch (JsonParseException e) {
      logger.info("createPersonListFromOriginalFile() JsonParseException", e);
    } catch (JsonMappingException e) {
      logger.info("createPersonListFromOriginalFile() JsonMappingException", e);
    } catch (IOException e) {
      logger.info("createPersonListFromOriginalFile() IOException", e);
    }
    logger.info("for begin");
    for (Person person : personList.person) {
      person.setId(UUID.randomUUID());
    }
    logger.info("for end");
    

  }

  public void createFireStationListAndIDFromOriginalFile() {
    try {
      fireStationList = iFirestationDao.getFireStationListDao();
    } catch (JsonParseException e) {
      logger.info("createFireStationListFromOriginalFile() JsonPArseException",
          e);
    } catch (JsonMappingException e) {
      logger.info(
          "createFireStationListFromOriginalFile() JsonMappingException", e);
    } catch (IOException e) {
      logger.info("createFireStationListFromOriginalFile() IOException", e);
    }
    for (FireStation fireStationId : fireStationList.fireStation) {
      fireStationId.setId(UUID.randomUUID());
    }
    
  }

  public void createHomeListFromPersonList() {
  
    for (Person person : personList.person) {
      UUID id = UUID.randomUUID();
      home.setAdress(person.getAddress());
      home.setCity(person.getCity());
      home.setZip(person.getZip());
      home.setId(id);
      person.setIdHome(id);
      homeList.addHome(home);
    }
    
  }

  public MedicalRecordList createMedicalRecordListAndIDFromOriginalList()
       {
    try {
      medicalRecordList = imedicalRecordDao.getMedicalRecordListDao();
    } catch (JsonParseException e) {
      logger.info("createMedicalRecordListFromOriginalList() JsonParseException",e);
    } catch (JsonMappingException e) {
      logger.info("createMedicalRecordListFromOriginalList() JsonMappingException",e);
    } catch (IOException e) {
      logger.info("createMedicalRecordListFromOriginalList() IOException",e);
    }
    for (MedicalRecord medicalRecordId : medicalRecordList.medicalRecord) {
      medicalRecordId.setId(UUID.randomUUID());
    }
    return medicalRecordList;
  }

  public void associateMedicalRecordIdWithRightPerson() {

    for (Person person : personList.person) {

      for (MedicalRecord medicalRecord : medicalRecordList.medicalRecord) {
        if (medicalRecord.getFirstName().equals(person.getFirstName())
            && medicalRecord.getLastName().equals(person.getLastName())) {
          person.setIdMedicalRecord(medicalRecord.getId());
          person.setBirthDate(medicalRecord.getBirthdate());
          person.setAge(
              Period.between(medicalRecord.getBirthdate(), LocalDate.now())
                  .getYears());

        }
      }
    }
  }

  public void associateFirestationWithRightHome() {
    for (FireStation firestation : fireStationList.fireStation) {
      for (Home home : homeList.home) {
        if (firestation.getAddress().equals(home.getAdress())) {
          home.setIdFirestation(firestation.getId());
          firestation.setHome(home.getId());
        }
      }
    }
  }

  public void createWorkingFileWithAssociatedProcessedData() {

    createPersonListAndIDFromOriginalFile();
    createFireStationListAndIDFromOriginalFile();  
    createMedicalRecordListAndIDFromOriginalList();
    createHomeListFromPersonList();
    associateMedicalRecordIdWithRightPerson(); 
    associateFirestationWithRightHome();
    
    
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
   
    try {
      objectMapper.writeValue(new File(WorkingFileOuput.getOriginalInputFile()), personList);
      objectMapper.writeValue(new File(WorkingFileOuput.getOriginalInputFile()), medicalRecordList);
      objectMapper.writeValue(new File(WorkingFileOuput.getOriginalInputFile()), fireStationList);
      objectMapper.writeValue(new File(WorkingFileOuput.getOriginalInputFile()), homeList);
    } catch (JsonGenerationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
 
  
  
  //TODO
}
