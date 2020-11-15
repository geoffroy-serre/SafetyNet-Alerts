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

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = FireStationController.class)
public class FireStationControllerTest {

  @MockBean
  private FireStationService fireStationService;

  @Autowired
  private FireStationController fireStationController;

  @Test
  public void getFireStationControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    fireStationController.getFireStationInfo();
    verify(fireStationService, times(1)).getFireStationList();
  }

  @Test
  public void JsonPArseExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationList())
        .thenThrow(JsonParseException.class);
    assertThatCode(() -> fireStationController.getFireStationInfo())
        .doesNotThrowAnyException();
  }

  @Test
  public void JsonMappingExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationList())
        .thenThrow(JsonMappingException.class);
    assertThatCode(() -> fireStationController.getFireStationInfo())
        .doesNotThrowAnyException();
  }

  @Test
  public void NoSuchFileExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationList())
        .thenThrow(NoSuchFileException.class);
    assertThatCode(() -> fireStationController.getFireStationInfo())
        .doesNotThrowAnyException();
  }

  @Test
  public void IoExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(fireStationService.getFireStationList()).thenThrow(IOException.class);
    assertThatCode(() -> fireStationController.getFireStationInfo())
        .doesNotThrowAnyException();
  }
}
