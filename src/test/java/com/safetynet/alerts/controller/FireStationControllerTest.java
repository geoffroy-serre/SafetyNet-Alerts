package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.services.FireStationService;
import com.safetynet.alerts.services.PersonService;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = FireStationController.class)
public class FireStationControllerTest {

  @MockBean
  private FireStationService fireStationService;
  
  @MockBean
  private PersonService personService;

  @Autowired
  private FireStationController fireStationController;

  @Test
  public void getFireStationControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    fireStationController.getFireStationInfoController();
    verify(fireStationService, times(1)).getFireStationListService();
  }
  
  @Test
  public void personsCoveredByAFireStationControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    int pFireStationNumber = 1;
    fireStationController.personsCoveredByAFirestationController(pFireStationNumber);
    verify(personService, times(1)).personsCoveredByAFirestationService(pFireStationNumber);
  }
  
  @Test
  public void postFireStationControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    fireStationController.postFireStationAdressMappingController();
    verify(fireStationService, times(1)).postFireStationAdressMappingService();
  }
  
  @Test
  public void updateFireStationControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    fireStationController.updateFireStationNumberController();
    verify(fireStationService, times(1)).updateFireStationNumberService();
  }
  
  @Test
  public void deleteFireStationControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    fireStationController.deleteFireStationAdressMappingController();
    verify(fireStationService, times(1)).deleteFireStationAdressMappingService();
  }

  @Test
  public void JsonPArseExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationListService())
        .thenThrow(JsonParseException.class);
    assertThatCode(() -> fireStationController.getFireStationInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void JsonMappingExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationListService())
        .thenThrow(JsonMappingException.class);
    assertThatCode(() -> fireStationController.getFireStationInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void NoSuchFileExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationListService())
        .thenThrow(NoSuchFileException.class);
    assertThatCode(() -> fireStationController.getFireStationInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void IoExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationListService()).thenThrow(IOException.class);
    assertThatCode(() -> fireStationController.getFireStationInfoController())
        .doesNotThrowAnyException();
  }
}
