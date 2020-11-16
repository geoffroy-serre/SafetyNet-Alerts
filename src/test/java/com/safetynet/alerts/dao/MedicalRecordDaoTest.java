package com.safetynet.alerts.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IMedicalRecordDao;

@WebMvcTest(value=IMedicalRecordDao.class)
public class MedicalRecordDaoTest {

  @Autowired
  IMedicalRecordDao medicalRecordDao;
  
  @Test
  public void getMedicalRecordListDtoTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    assertNotNull(medicalRecordDao.getMedicalRecordListDto());
  }
  
  @Test
  public void postNewMedicalRecordTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    assertNotNull(medicalRecordDao.postNewMedicalRecord());
  }
  
  @Test
  public void updateMedicalRecordTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    assertNotNull(medicalRecordDao.updateMedicalRecord());
  }
  
  @Test
  public void deleteMedicalRecordTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    assertNotNull(medicalRecordDao.deleteMedicalRecord());
  }
  

}
