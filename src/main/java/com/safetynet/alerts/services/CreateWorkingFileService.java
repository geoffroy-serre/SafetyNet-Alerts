package com.safetynet.alerts.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

public class CreateWorkingFileService {
  @Autowired
  PersonList personList;
  @Autowired
  FireStationList fireStationList;
  @Autowired
  HomeList homeList;
  @Autowired
  MedicalRecordList medicalRecordList;
  @Autowired
  IPersonDao ipersonDao;
  @Autowired
  IFireStationDao iFirestationDao;
  @Autowired
  IMedicalRecordDao imedicalRecordDao;

  @Autowired
  Home home;

  private static final Logger logger = LogManager.getLogger("App");

  public PersonList createPersonListAndIDFromOriginalFile() {
    try {
      personList = ipersonDao.getPersonListDao();
    } catch (JsonParseException e) {
      logger.info("createPersonListFromOriginalFile() JsonParseException", e);
    } catch (JsonMappingException e) {
      logger.info("createPersonListFromOriginalFile() JsonMappingException", e);
    } catch (IOException e) {
      logger.info("createPersonListFromOriginalFile() IOException", e);
    }
    for (Person personId : personList.person) {
      personId.setId(UUID.randomUUID());
    }

    return personList;

  }

  public FireStationList createFireStationListAndIDFromOriginalFile() {
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
    return fireStationList;
  }

  public HomeList createHomeListFromPersonList() {
    PersonList personList = createPersonListAndIDFromOriginalFile();
    for (Person person : personList.person) {
      UUID id = UUID.randomUUID();
      home.setAdress(person.getAddress());
      home.setCity(person.getCity());
      home.setZip(person.getZip());
      home.setId(id);
      person.setIdHome(id);
      homeList.addHome(home);
    }
    return homeList;
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

  }
 //TODO
}
