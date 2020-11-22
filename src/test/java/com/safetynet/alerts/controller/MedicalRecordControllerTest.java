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
import com.safetynet.alerts.utils.WorkingFileOuput;

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
    medicalRecordController.getMedicalRecordController();
    verify(medicalRecordService, times(1)).getMedicalRecordsListService(WorkingFileOuput.getWorkingInputFile());
  }
  @Test
  public void postNewMedicalRecordControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    medicalRecordController.postNewMedicalRecordController();
    verify(medicalRecordService, times(1)).postNewMedicalRecordService();
  }
  @Test
  public void updateMedicalRecordControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    medicalRecordController.updateMedicalRecordController();
    verify(medicalRecordService, times(1)).updateMedicalRecordService();
  }
  @Test
  public void deleteMedicalRecordControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    medicalRecordController.deleteMedicalRecordController();
    verify(medicalRecordService, times(1)).deleteMedicalRecordService();
  }

  @Test
  public void JsonPArseExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsListService("filepath"))
        .thenThrow(JsonParseException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecordController())
        .doesNotThrowAnyException();
  }

  @Test
  public void JsonMappingExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsListService("filepath"))
        .thenThrow(JsonMappingException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecordController())
        .doesNotThrowAnyException();
  }

  @Test
  public void NoSuchFileExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsListService("filepath"))
        .thenThrow(NoSuchFileException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecordController())
        .doesNotThrowAnyException();
  }

  @Test
  public void IoExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(medicalRecordService.getMedicalRecordsListService("filepath"))
        .thenThrow(IOException.class);
    assertThatCode(() -> medicalRecordController.getMedicalRecordController())
        .doesNotThrowAnyException();
  }
}
