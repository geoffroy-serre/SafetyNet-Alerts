package com.safetynet.alerts.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IPersonDao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

@WebMvcTest(value =PersonService.class)
public class PersonServiceTest {
  
  @Autowired
  PersonService personService;
  
  @MockBean
  IPersonDao iPersonDao;
  
    
  @Test
  public void getPersonListServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    personService.getpersonsService();

    verify(iPersonDao, times(1)).getPersonListDao();
  }

  @Test
  public void postNewPersonServiceTest() {
    personService.postNewPersonService();
    verify(iPersonDao, times(1)).postNewPersonDao();
  }
  
  @Test
  public void childListForAnAdressServiceTest() {
    String pAdress = "Adress Test";
    personService.childListForAnAdressService(pAdress);
    verify(iPersonDao, times(1)).childListForAnAdressDao(pAdress);
  }
  
  @Test
  public void personCoveredByAFireStationServiceTest() {
    int pFireStationNumber = 1;
    personService.personsCoveredByAFirestationService(pFireStationNumber);
    verify(iPersonDao, times(1)).personsCoveredByAFirestationDao(pFireStationNumber);
  }
  
  @Test
  public void detailledPersonInfoServiceTest() {
    String pfirstName = "Edward";
    String plastName = "TheGreat";
    personService.detailledPersonInfoService(pfirstName, plastName);
    verify(iPersonDao, times(1)).detailledPersonInfoDao(pfirstName, plastName);
  }
  
  @Test
  public void floodPersonListCompleteServiceTest() {
    ArrayList<Integer> pListFireStationNumber = new ArrayList<Integer>();
    pListFireStationNumber.add(1);
    pListFireStationNumber.add(2);
    pListFireStationNumber.add(3);
    
    personService.floodPersonListCompleteService(pListFireStationNumber);
    verify(iPersonDao, times(1)).floodPersonListCompleteDao(pListFireStationNumber);
  }
  
  @Test
  public void getAllEmailForACityServiceTest() {
    String pCity = "Toulouse";
    
    personService.getAllEmailForACityService(pCity);
    verify(iPersonDao, times(1)).getAllEmailForACityDao(pCity); 
  }
  
  @Test
  public void personListWithCompleteInfoCoveredByFirestationServiceTest() {
    String pAdress = "85 rue du gnou";
    
    personService.personListWithCompleteInfoCoveredByFireStationService(pAdress);
    verify(iPersonDao, times(1)).personListWithCompleteInfoCoveredByFireStationDao(pAdress);
  }
  
  @Test
  public void phoneListOfResidentForAGivenFireStationServiceTest() {
    int pFireStationNumber = 1;
    personService.phoneListOfResidentForAGivenFireStationService(pFireStationNumber);
    verify(iPersonDao, times(1)).phoneListOfResidentForAGivenFireStationDao(pFireStationNumber);
  }
  
  @Test
  public void updateAPersonServiceTest() {
    personService.updateAPersonService();
    verify(iPersonDao, times(1)).updateAPersonDao();
  }
  
  @Test
  public void deleteAPersonServiceTest() {
    personService.deleteAPersonService();
    verify(iPersonDao, times(1)).deleteAPersonDao();
  }
}
