package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.interfaces.IFireStationDao;
import com.safetynet.alerts.interfaces.IHomeDao;
import com.safetynet.alerts.interfaces.IMedicalRecordDao;
import com.safetynet.alerts.interfaces.IPersonDao;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.HomeList;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonMedicalFireStationWrapper;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.utils.WorkingFileOuput;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonDaoImpl.
 */
@Repository
public class PersonDaoImpl implements IPersonDao {

  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger("App");

  /**
   * Gets the person list dao.
   *
   * @param filePathData the file path data
   * @return the person list dao
   */
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public PersonList getPersonListDao(String filePathData) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
        true);
    objectMapper.registerModule(new JavaTimeModule());

    PersonList personsList = new PersonList();
    try {
      personsList = objectMapper.readValue(new File(filePathData),
          PersonList.class);
    } catch (JsonParseException e) {
      logger.error("JsonParseException getting person List", e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException getting person List", e);
    } catch (IOException e) {
      logger.error("IOException getting person List", e);
    }

    logger.info("PersonList retrieval");

    return personsList;
  }

  /**
   * Post new person dao.
   *
   * @return the person
   */
  @Override
  public Person postNewPersonDao() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Update A person dao.
   *
   * @return the person
   */
  @Override
  public Person updateAPersonDao() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Delete A person dao.
   *
   * @return the person
   */
  @Override
  public Person deleteAPersonDao() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Persons covered by A firestation dao.
   *
   * @param pFireStationNumber the fire station number
   * @return the array list
   * @throws JsonParseException   the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException          Signals that an I/O exception has occurred.
   */
  @Override
  public ArrayList<PersonMedicalFireStationWrapper> floodPersonsCoveredByAFirestationDao(
      ArrayList<Integer> pFireStationNumber) {
    String filePath = WorkingFileOuput.getWorkingInputFile();

    /*
     * Get Firestation's and addThem to fireStationHomeIdList only save the
     * firestation with the number given in parameters
     */
    ArrayList<FireStation> fireStationHomeIdList = new ArrayList<FireStation>();
    IFireStationDao firestationDao = new FireStationDaoImpl();
    FireStationList fireStationList = firestationDao
        .getFireStationListDao(filePath);

    for (FireStation fireStation : fireStationList.fireStation) {
      for (int stationNumber : pFireStationNumber) {
        if (fireStation.getStation() == stationNumber) {
          fireStationHomeIdList.add(fireStation);
        }
      }
    }

    /*
     * Browsing PersonList with same HomeId from fireStationHomeIdList and add
     * them into coveredPersonList
     * 
     */

    PersonList personList = getPersonListDao(filePath);
    ArrayList<Person> coveredPersonList = new ArrayList<Person>();
    for (Person person : personList.person) {
      for (FireStation fireStation : fireStationHomeIdList) {
        if (person.getIdHome().equals(fireStation.getHome())) {
          coveredPersonList.add(person);

        }
      }
    }

    /*
     * Get the medicalRecord of persons using coveredPersonList
     */
    ArrayList<PersonMedicalFireStationWrapper> resultList = new ArrayList<PersonMedicalFireStationWrapper>();
    IMedicalRecordDao medicalRecordDao = new MedicalRecordDaoImpl();
    MedicalRecordList medicalRecordList = medicalRecordDao
        .getMedicalRecordListDao(filePath);
    for (Person person : coveredPersonList) {
      for (MedicalRecord medicalRecord : medicalRecordList.medicalRecord) {
        /*
         * Create and populate resultList<PersonAndMedical> with
         * coveredPersonList and their medical record
         * 
         */
        if (person.getIdMedicalRecord().equals(medicalRecord.getId())) {
          PersonMedicalFireStationWrapper persons = new PersonMedicalFireStationWrapper();
          persons.setMedicalRecord(medicalRecord);
          persons.setPerson(person);
          resultList.add(persons);
        }
      }
    }

    /**
     * Setting to null infos we dont want to send.
     */
    for (PersonMedicalFireStationWrapper personAndMedical : resultList) {
      personAndMedical.getPerson().setId(null);
      personAndMedical.getPerson().setBirthDate(null);
      personAndMedical.getPerson().setIdHome(null);
      personAndMedical.getPerson().setIdHome(null);
      personAndMedical.getPerson().setCellNumber(null);
      personAndMedical.getMedicalRecord().setId(null);
      personAndMedical.getMedicalRecord().setBirthdate(null);
      personAndMedical.getPerson().setIdMedicalRecord(null);
    }

    return resultList;
  }

  /**
   * Child list for an adress dao.
   *
   * @param pAdress the adress
   * @return the person list
   */
  @Override
  public PersonList childListForAnAdressDao(String pAdress) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Phone list of resident for A given fire station dao.
   *
   * @param pFireStationNumber the fire station number
   * @return the array list
   */
  @Override
  public ArrayList<String> phoneListOfResidentForAGivenFireStationDao(
      int pFireStationNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Person list with complete info covered by fire station dao.
   *
   * @param pAdress the adress
   * @return the person list
   */
  @Override
  public ArrayList<PersonMedicalFireStationWrapper> personListWithCompleteInfoCoveredByFireStationDao(
      String pAdress) {
    String filePath = WorkingFileOuput.getWorkingInputFile();

    /**
     * Getting Home Id for given Adress
     */
    UUID homeID = null;
    IHomeDao homeDao = new HomeDaoImpl();
    HashSet<Home> homeList = new HashSet<Home>();
    homeList = homeDao.getHomeListDao(filePath);
    for (Home home : homeList) {
      if (home.getAdress().equalsIgnoreCase(pAdress)) {
        homeID = home.getId();
      }
    }
    /**
     * Getting Person for given adress And their medical Record And
     * FireStationNumber. Adding result to preResult List
     */
    IFireStationDao firestationDao = new FireStationDaoImpl();
    FireStationList fireStationList = new FireStationList();
    fireStationList = firestationDao.getFireStationListDao(filePath);
    IMedicalRecordDao medicalRecordDao = new MedicalRecordDaoImpl();
    MedicalRecordList medicalRecordList = medicalRecordDao
        .getMedicalRecordListDao(filePath);
    ArrayList<PersonMedicalFireStationWrapper> preResult = new ArrayList<PersonMedicalFireStationWrapper>();
    PersonList personList = getPersonListDao(filePath);

    for (Person person : personList.person) {
      logger.info("Enter person list:"
                  + person.getIdHome()
                  + " "
                  + homeID);
      for (MedicalRecord medicalRecord : medicalRecordList.medicalRecord) {
        logger.info("Enter medical list");
        for (FireStation firestation : fireStationList.fireStation) {
          logger.info("Enter fire list");
          if (person.getIdHome().equals(homeID)
              && medicalRecord.getId().equals(person.getIdMedicalRecord())
              && firestation.getHome().equals(homeID)) {
            logger.info("Match found");
            PersonMedicalFireStationWrapper persons = new PersonMedicalFireStationWrapper();
            persons.setPerson(person);
            persons.setFirestation(firestation);
            persons.setMedicalRecord(medicalRecord);
            preResult.add(persons);
          }
        }
      }
    }

    /**
     * Setting non wantend properties to null to avoid presence in result
     */
    for (PersonMedicalFireStationWrapper data : preResult) {
      data.getFirestation().setId(null);
      data.getFirestation().setHome(null);
      data.getFirestation().setAddress(null);
      data.getMedicalRecord().setId(null);
      data.getMedicalRecord().setBirthdate(null);
      data.getPerson().setId(null);
      data.getPerson().setBirthDate(null);
      data.getPerson().setIdHome(null);
      data.getPerson().setIdMedicalRecord(null);
    }

    return preResult;

  }

  /**
   * Detailled person info dao.
   *
   * @param pfirstName the pfirst name
   * @param plastName  the plast name
   * @return the array list
   */
  @Override
  public ArrayList<PersonMedicalFireStationWrapper> detailledPersonInfoDao(
      String pfirstName, String plastName) {
    String filePath = WorkingFileOuput.getWorkingInputFile();

    PersonList list = new PersonList();
    list = getPersonListDao(filePath);

    ArrayList<Person> processedPersonList = new ArrayList<Person>();
    for (Person person : list.person) {
      if (person.getLastName().equalsIgnoreCase(plastName)
          && person.getFirstName().equalsIgnoreCase(pfirstName)) {
        processedPersonList.add(person);
      }
    }
    IMedicalRecordDao medicalRecordList = new MedicalRecordDaoImpl();
    MedicalRecordList processedMedicalRecordList = new MedicalRecordList();

    processedMedicalRecordList = medicalRecordList
        .getMedicalRecordListDao(WorkingFileOuput.getWorkingInputFile());

    ArrayList<PersonMedicalFireStationWrapper> result = new ArrayList<PersonMedicalFireStationWrapper>();
    for (Person person : processedPersonList) {
      UUID personId = person.getIdMedicalRecord();
      for (MedicalRecord medicalRecord : processedMedicalRecordList.medicalRecord) {
        if (medicalRecord.getId().equals(personId)) {
          PersonMedicalFireStationWrapper persons = new PersonMedicalFireStationWrapper();
          persons.setMedicalRecord(medicalRecord);
          persons.setPerson(person);
          person.setAge(
              Period.between(medicalRecord.getBirthdate(), LocalDate.now())
                  .getYears());
          result.add(persons);
        }
      }
    }
    /**
     * Setting to null infos we dont want to send
     */
    for (PersonMedicalFireStationWrapper personAndMedical : result) {
      personAndMedical.getPerson().setId(null);
      personAndMedical.getPerson().setBirthDate(null);
      personAndMedical.getPerson().setIdHome(null);
      personAndMedical.getPerson().setIdHome(null);
      personAndMedical.getPerson().setCellNumber(null);
      personAndMedical.getMedicalRecord().setId(null);
      personAndMedical.getMedicalRecord().setBirthdate(null);
      personAndMedical.getPerson().setIdMedicalRecord(null);
    }

    return result;
  }

  /**
   * Gets the all email for A city dao.
   *
   * @param pCity the city
   * @return the all email for A city dao
   */
  @Override
  public HashSet<String> getAllEmailForACityDao(String pCity) {
    String filePath = WorkingFileOuput.getWorkingInputFile();

    PersonList list = new PersonList();

    list = getPersonListDao(filePath);

    HashSet<String> emailList = new HashSet<String>();
    for (Person person : list.person) {
      if (person.getCity().equalsIgnoreCase(pCity)) {
        emailList.add(person.getEmail());
      }
    }

    return emailList;
  }

}
