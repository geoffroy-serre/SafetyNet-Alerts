package com.safetynet.alerts.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IPersonDto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@WebMvcTest(value =PersonService.class)
public class PersonServiceTest {
  
  @Autowired
  PersonService personService;
  
  @MockBean
  IPersonDto iPersonDto;
  
    
  @Test
  public void getPersonListServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    personService.getpersons();

    verify(iPersonDto, times(1)).getPersonListDto();
  }
}
