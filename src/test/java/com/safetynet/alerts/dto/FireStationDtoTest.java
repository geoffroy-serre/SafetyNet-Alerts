package com.safetynet.alerts.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IFireStationDto;

@WebMvcTest(value=IFireStationDto.class)
public class FireStationDtoTest {

  @Autowired
  IFireStationDto fireStationDto;
  
  @Test
  public void getFireStationListDtoTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    assertNotNull(fireStationDto.getFireStationListDto());
  }
  

}
