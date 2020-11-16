package com.safetynet.alerts.interfaces;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;

public interface IPersonDao {
  
  public PersonList getPersonListDto() throws JsonParseException, JsonMappingException, IOException;

  public Person postNewPerson();

  public Person updateAPerson();

  public Person deleteAPerson();

  public PersonList personsCoveredByAFirestation(int pFireStationNumber);

  public PersonList childListForAnAdress(String pAdress);

  public ArrayList<String> phoneListOfResidentForAGivenFireStation(
      String pFireStationNumber);

  public PersonList personListWithCompleteInfoCoveredByFireStation(
      String pAdress);

  public PersonList floodPersonListComplete(
      ArrayList<Integer> pListFireStationNumber);

  public Person detailledPersonInfo(String pfirstName, String plastName);

  public ArrayList<String> getAllEmailForACity(String pCity);
  
}
