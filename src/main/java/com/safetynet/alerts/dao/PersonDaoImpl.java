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
import com.safetynet.alerts.interfaces.IMedicalRecordDao;
import com.safetynet.alerts.interfaces.IPersonDao;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.MedicalRecordList;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedical;
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
   * @throws JsonParseException   the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException          Signals that an I/O exception has occurred.
   */
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public PersonList getPersonListDao(String filePathData)
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
        true);
    objectMapper.registerModule(new JavaTimeModule());

    PersonList personsList = objectMapper.readValue(new File(filePathData),
        PersonList.class);

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
  public ArrayList<PersonAndMedical> personsCoveredByAFirestationDao(
      ArrayList<Integer> pFireStationNumber)
      throws JsonParseException, JsonMappingException, IOException {
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
    IPersonDao personDao = new PersonDaoImpl();
    PersonList personList = personDao.getPersonListDao(filePath);
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
    ArrayList<PersonAndMedical> resultList = new ArrayList<PersonAndMedical>();
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
          PersonAndMedical persons = new PersonAndMedical();
          persons.setMedicalRecord(medicalRecord);
          persons.setPerson(person);
          resultList.add(persons);
        }
      }
    }

    /**
     * Setting to null infos we dont want to send.
     */
    for (PersonAndMedical personAndMedical : resultList) {
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
  public PersonList personListWithCompleteInfoCoveredByFireStationDao(
      String pAdress) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Flood person list complete dao.
   *
   * @param pListFireStationNumber the list fire station number
   * @return the person list
   */
  @Override
  public PersonList floodPersonListCompleteDao(
      ArrayList<Integer> pListFireStationNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Detailled person info dao.
   *
   * @param pfirstName the pfirst name
   * @param plastName  the plast name
   * @return the array list
   */
  @Override
  public ArrayList<PersonAndMedical> detailledPersonInfoDao(String pfirstName,
      String plastName) {
    String filePath = WorkingFileOuput.getWorkingInputFile();

    PersonList list = new PersonList();
    try {
      list = getPersonListDao(filePath);
    } catch (JsonParseException e) {
      logger.error("JsonParseException getting email for file: "
                  + filePath
                  + " ",
          e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException getting email for file: "
                  + filePath
                  + " ",
          e);
    } catch (IOException e) {
      logger.error("IOException getting email for file: "
                  + filePath
                  + " ",
          e);
    }

    ArrayList<Person> processedPersonList = new ArrayList<Person>();
    for (Person person : list.person) {
      if (person.getLastName().equalsIgnoreCase(plastName)
          && person.getFirstName().equalsIgnoreCase(pfirstName)) {
        processedPersonList.add(person);
      }
    }
    IMedicalRecordDao medicalRecordList = new MedicalRecordDaoImpl();
    MedicalRecordList processedMedicalRecordList = new MedicalRecordList();
    try {
      processedMedicalRecordList = medicalRecordList
          .getMedicalRecordListDao(WorkingFileOuput.getWorkingInputFile());
    } catch (JsonParseException e) {
      logger.error("JsonParseException getting Medical Record for file: "
                  + filePath
                  + " ",
          e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException getting Medical Record for file: "
                  + filePath
                  + " ",
          e);
    } catch (IOException e) {
      logger.error("IOException getting Medical Record for file: "
                  + filePath
                  + " ",
          e);
    }

    ArrayList<PersonAndMedical> result = new ArrayList<PersonAndMedical>();
    for (Person person : processedPersonList) {
      UUID personId = person.getIdMedicalRecord();
      for (MedicalRecord medicalRecord : processedMedicalRecordList.medicalRecord) {
        if (medicalRecord.getId().equals(personId)) {
          PersonAndMedical persons = new PersonAndMedical();
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
    for (PersonAndMedical personAndMedical : result) {
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
    try {
      list = getPersonListDao(filePath);
    } catch (JsonParseException e) {
      logger.info("JsonParseException getting email for file: "
                  + filePath
                  + " ",
          e);
    } catch (JsonMappingException e) {
      logger.info("JsonMappingException getting email for file: "
                  + filePath
                  + " ",
          e);
    } catch (IOException e) {
      logger.info("IOException getting email for file: "
                  + filePath
                  + " ",
          e);
    }
    HashSet<String> emailList = new HashSet<String>();
    for (Person person : list.person) {
      if (person.getCity().equalsIgnoreCase(pCity)) {
        emailList.add(person.getEmail());
      }
    }

    return emailList;
  }

}
