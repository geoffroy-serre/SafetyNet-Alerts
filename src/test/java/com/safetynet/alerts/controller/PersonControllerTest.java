package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.services.PersonService;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

  @MockBean
  private PersonService personService;

  @Autowired
  private PersonController personController;

  @Test
  public void getPersonInfoControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    personController.getPersonInfoController();
    verify(personService, times(1)).getpersonsService();
  }

  @Test
  public void postNewPersonControllerTest() {
    personController.postNewPersonController();
    verify(personService, times(1)).postNewPersonService();
  }
  
  @Test
  public void childListForAnAdressControllerTest() {
    String pAdress = "Adress Test";
    personController.childListForAnAdressController(pAdress);
    verify(personService, times(1)).childListForAnAdressService(pAdress);
  }
  
  @Test
  public void detailledPersonInfoControllerTest() {
    String pfirstName = "Edward";
    String plastName = "TheGreat";
    personController.detailledPersonInfoController(pfirstName, plastName);
    verify(personService, times(1)).detailledPersonInfoService(pfirstName, plastName);
  }
  
  @Test
  public void floodPersonListCompleteControllerTest() {
    ArrayList<Integer> pListFireStationNumber = new ArrayList<Integer>();
    pListFireStationNumber.add(1);
    pListFireStationNumber.add(2);
    pListFireStationNumber.add(3);
    
    personController.floodPersonListCompleteController(pListFireStationNumber);
    verify(personService, times(1)).floodPersonListCompleteService(pListFireStationNumber);
  }
  
  @Test
  public void getAllEmailForACityControllerTest() {
    String pCity = "Toulouse";
    
    personController.getAllEmailForACityController(pCity);
    verify(personService, times(1)).getAllEmailForACityService(pCity); 
  }
  
  @Test
  public void personListWithCompleteInfoCoveredByFirestationControllerTest() {
    String pAdress = "85 rue du gnou";
    
    personController.personListWithCompleteInfoCoveredByFireStationController(pAdress);
    verify(personService, times(1)).personListWithCompleteInfoCoveredByFireStationService(pAdress);
  }
  
  @Test
  public void phoneListOfResidentForAGivenFireStationControllerTest() {
    int pFireStationNumber = 1;
    personController.phoneListOfResidentForAGivenFireStationController(pFireStationNumber);
    verify(personService, times(1)).phoneListOfResidentForAGivenFireStationService(pFireStationNumber);
  }
  
  @Test
  public void updateAPersonControllerTest() {
    personController.updateAPersonController();
    verify(personService, times(1)).updateAPersonService();
  }
  
  @Test
  public void deleteAPersonControllerTest() {
    personController.deleteAPersonController();
    verify(personService, times(1)).deleteAPersonService();
  }
  
  @Test
public void JsonPArseExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(personService.getpersonsService()).thenThrow(JsonParseException.class);
    assertThatCode(() -> personController.getPersonInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void JsonMappingExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(personService.getpersonsService()).thenThrow(JsonMappingException.class);
    assertThatCode(() -> personController.getPersonInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void NoSuchFileExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(personService.getpersonsService()).thenThrow(NoSuchFileException.class);
    assertThatCode(() -> personController.getPersonInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void IoExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(personService.getpersonsService()).thenThrow(IOException.class);
    assertThatCode(() -> personController.getPersonInfoController())
        .doesNotThrowAnyException();
  }
}
