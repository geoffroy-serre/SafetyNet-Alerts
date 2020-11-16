package com.safetynet.alerts.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.dao.MedicalRecordDtoImpl;
import com.safetynet.alerts.interfaces.IMedicalRecordDao;
import com.safetynet.alerts.model.MedicalRecordList;
import com.safetynet.alerts.services.MedicalRecordService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@WebMvcTest(value =MedicalRecordService.class)
public class MedicalRecordListServiceTest {
  
  @Autowired
  MedicalRecordService medicalRecord;
  
  @MockBean
  IMedicalRecordDao medicalRecordDto;
  
  @Test
  public void getMedicalRecordServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    
    medicalRecord.getMedicalRecordsList();

    verify(medicalRecordDto, times(1)).getMedicalRecordListDto();
  }
}
