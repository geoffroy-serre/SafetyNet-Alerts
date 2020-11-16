package com.safetynet.alerts.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IFireStationDao;

@WebMvcTest(value=IFireStationDao.class)
public class FireStationDaoTest {

  @Autowired
  IFireStationDao fireStationDto;
  
  @Test
  public void getFireStationListDtoTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    assertNotNull(fireStationDto.getFireStationListDto());
  }
  

}