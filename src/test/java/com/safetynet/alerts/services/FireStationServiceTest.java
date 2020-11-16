package com.safetynet.alerts.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IFireStationDao;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@WebMvcTest(value =FireStationService.class)
public class FireStationServiceTest {
  
  @Autowired
  FireStationService fireStationService;
  
  @MockBean
  IFireStationDao iFireStationDto;
  
    
  @Test
  public void getPersonListServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    fireStationService.getFireStationList();

    verify(iFireStationDto, times(1)).getFireStationListDto();
  }
  
  @Test
  public void updateFireStationAdressMappingServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    fireStationService.updateFireStationNumber();

    verify(iFireStationDto, times(1)).updateFireStationNumber();
  }
  
  @Test
  public void postFireStationAdressMapping() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    fireStationService.postFireStationAdressMapping();

    verify(iFireStationDto, times(1)).postFireStationMapping();
  }
  
  @Test
  public void deleteFireStationAdressMappingTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    fireStationService.deleteFireStationAdressMapping();

    verify(iFireStationDto, times(1)).deleteFireStationMapping();
  }
}
