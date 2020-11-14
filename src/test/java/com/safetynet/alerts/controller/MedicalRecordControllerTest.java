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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@WebMvcTest(value =MedicalRecordController.class)
public class MedicalRecordControllerTest {
  
  @MockBean
  private MedicalRecordService medicalRecordService;
  
  @Autowired
  private MedicalRecordController medicalRecordController;
  
 
  
 @Test
  public void getPersonListServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
   medicalRecordController.getMedicalRecord(); 
   verify(medicalRecordService, times(1)).getMedicalRecordsList();
  }
}

