package com.safetynet.alerts.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonMedicalFireStationWrapper;
import com.safetynet.alerts.model.PersonList;


/**
 * The Interface IPersonDao.
 */
public interface IPersonDao {
  
  /**
   * Gets the person list dao.
   *
   * @param filePathData the file path data
   * @return the person list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PersonList getPersonListDao(String filePathData) throws JsonParseException, JsonMappingException, IOException;

  /**
   * Post new person dao.
   *
   * @return the person
   */
  public Person postNewPersonDao();

  /**
   * Update A person dao.
   *
   * @return the person
   */
  public Person updateAPersonDao();

  /**
   * Delete A person dao.
   *
   * @return the person
   */
  public Person deleteAPersonDao();

  /**
   * Persons covered by A firestation dao.
   *
   * @param fireStationNumber the fire station number
   * @return the array list
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ArrayList<PersonMedicalFireStationWrapper> floodPersonsCoveredByAFirestationDao(ArrayList<Integer> fireStationNumber);

  /**
   * Child list for an adress dao.
   *
   * @param pAdress the adress
   * @return the person list
   */
  public PersonList childListForAnAdressDao(String pAdress);

  /**
   * Phone list of resident for A given fire station dao.
   *
   * @param pFireStationNumber the fire station number
   * @return the array list
   */
  public ArrayList<String> phoneListOfResidentForAGivenFireStationDao(
      int pFireStationNumber);

  /**
   * Person list with complete info covered by fire station dao.
   *
   * @param pAdress the adress
   * @return the person list
   */
  public ArrayList<PersonMedicalFireStationWrapper> personListWithCompleteInfoCoveredByFireStationDao(
      String pAdress);


  /**
   * Detailled person info dao.
   *
   * @param pfirstName the pfirst name
   * @param plastName the plast name
   * @return the array list
   */
  public ArrayList<PersonMedicalFireStationWrapper> detailledPersonInfoDao(String pfirstName, String plastName);

  /**
   * Gets the all email for A city dao.
   *
   * @param pCity the city
   * @return the all email for A city dao
   */
  public HashSet<String> getAllEmailForACityDao(String pCity);
  
}
