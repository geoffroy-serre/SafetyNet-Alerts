package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.services.MedicalRecordService;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = MedicalRecordController.class)
public class MedicalRecordControllerTest {

  @MockBean
  private MedicalRecordService medicalRecordService;

  @Autowired
  private MedicalRecordController medicalRecordController;

  @Test
  public void getMedicalRecordControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    medicalRecordController.getMedicalRecord();
    verify(medicalRecordService, times(1)).getMedicalRecordsList();
  }
  @Test
  public void postNewMedicalRecordControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    medicalRecordController.postNewMedicalRecord();
    verify(medicalRecordService, times(1)).postNewMedicalRecord();
  }
  @Test
  public void updateMedicalRecordControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    medicalRecordController.updateMedicalRecord();
    verify(medicalRecordService, times(1)).updateMedicalRecord();
  }
  @Test
  public void deleteMedicalRecordControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    medicalRecordController.deleteMedicalRecord();
    verify(medicalRecordService, times(1)).deleteMedicalRecord();
  }

  @Test
  public void JsonPArseExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsList())
        .thenThrow(JsonParseException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecord())
        .doesNotThrowAnyException();
  }

  @Test
  public void JsonMappingExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsList())
        .thenThrow(JsonMappingException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecord())
        .doesNotThrowAnyException();
  }

  @Test
  public void NoSuchFileExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsList())
        .thenThrow(NoSuchFileException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecord())
        .doesNotThrowAnyException();
  }

  @Test
  public void IoExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsList())
        .thenThrow(IOException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecord())
        .doesNotThrowAnyException();
  }
}
