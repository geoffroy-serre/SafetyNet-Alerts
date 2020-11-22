package com.safetynet.alerts.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

/**
 * The Class CreateWorkingFileService.
 */
@Service
public class CreateWorkingFileService {

  /** The person list. */
  @Autowired
  PersonList personList;

  /** The fire station list. */
  @Autowired
  FireStationList fireStationList;

  /** The home list. */
  // @Autowired
  // HomeList homeList;

  /** The medical record list. */
  @Autowired
  MedicalRecordList medicalRecordList;

  /** The iperson dao. */
  @Autowired
  IPersonDao ipersonDao;

  /** The i firestation dao. */
  @Autowired
  IFireStationDao iFirestationDao;

  /** The imedical record dao. */
  @Autowired
  IMedicalRecordDao imedicalRecordDao;

  ArrayList<Home> homeList = new ArrayList<Home>();
  HashSet<Home> homeSet = new HashSet<Home>();

  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger("App");

  /**
   * Creates the person list and ID from original file.
   *
   * @param filePath the file path
   * @return the person list
   */
  public PersonList createPersonListAndIDFromOriginalFile(String filePath) {
    try {
      personList = ipersonDao.getPersonListDao(filePath);
      logger.info("list getted");
    } catch (JsonParseException e) {
      logger.info("createPersonListFromOriginalFile() JsonParseException", e);
    } catch (JsonMappingException e) {
      logger.info("createPersonListFromOriginalFile() JsonMappingException", e);
    } catch (IOException e) {
      logger.info("createPersonListFromOriginalFile() IOException", e);
    }

    for (Person person : personList.person) {
      person.setId(UUID.randomUUID());
    }

    return personList;

  }

  /**
   * Creates the fire station list and ID from original file.
   *
   * @param filePath the file path
   */
  public void createFireStationListAndIDFromOriginalFile(String filePath) {

    fireStationList = iFirestationDao.getFireStationListDao(filePath);

    for (FireStation fireStation : fireStationList.fireStation) {
      fireStation.setId(UUID.randomUUID());
    }

  }

  /**
   * Creates the adress list of fire station.
   */
  public void createAdressListOfFireStation() {

  }

  /**
   * Creates the home list from person list.
   */
  public void createHomeListFromPersonList() {

    for (Person person : personList.person) {
      Home home = new Home();
      UUID id = UUID.randomUUID();

      home.setAdress(person.getAddress());
      home.setCity(person.getCity());
      home.setZip(person.getZip());
      if (!homeSet.contains(home)) {
      home.setId(id);
      
        person.setIdHome(id);
        homeSet.add(home);
      }

    }
    
    ArrayList<Home> homeList2 = new ArrayList<Home>(homeSet);
    homeList = homeList2;

  }

  /**
   * Creates the medical record list and ID from original list.
   *
   * @param pathToData the path to data
   * @return the medical record list
   */
  public MedicalRecordList createMedicalRecordListAndIDFromOriginalList(
      String pathToData) {

    medicalRecordList = imedicalRecordDao.getMedicalRecordListDao(pathToData);

    for (MedicalRecord medicalRecordId : medicalRecordList.medicalRecord) {
      medicalRecordId.setId(UUID.randomUUID());
    }
    return medicalRecordList;
  }

  /**
   * Associate medical record id with right person.
   */
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

  /**
   * Associate firestation with right home.
   */
  public void associateFirestationWithRightHome() {
    for (FireStation firestation : fireStationList.fireStation) {
      for (Home home : homeSet) {
        if (firestation.getAddress().equals(home.getAdress())) {
          home.setIdFirestation(firestation.getId());
          firestation.setHome(home.getId());
        }
      }
    }
  }

  /**
   * Creates the working file with associated processed data.
   *
   * @param filePath the file path
   */
  public void createWorkingFileWithAssociatedProcessedData(String filePath) {

    createPersonListAndIDFromOriginalFile(filePath);
    createFireStationListAndIDFromOriginalFile(filePath);
    createMedicalRecordListAndIDFromOriginalList(filePath);
    createHomeListFromPersonList();
    associateMedicalRecordIdWithRightPerson();
    associateFirestationWithRightHome();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
        true);
    // objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.registerModule(new JavaTimeModule());

    File file = new File(WorkingFileOuput.getWorkingInputFile());

    HashMap<String, ArrayList<?>> list = new HashMap<String, ArrayList<?>>();

    list.put("Person", personList.person);
    list.put("Firestation", fireStationList.fireStation);
    list.put("Home", homeList);
    list.put("MedicalRecord", medicalRecordList.medicalRecord);

    try {
      objectMapper.writeValue(file, list);

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

}
