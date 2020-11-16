package com.safetynet.alerts.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IPersonDao;

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
  IPersonDao iPersonDao;
  
    
  @Test
  public void getPersonListServiceTest() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException {
    personService.getpersons();

    verify(iPersonDao, times(1)).getPersonListDto();
  }
  
  @Test
  public void postNewPersonServiceTest() {
    personService.postNewPerson();
    verify (iPersonDao,times(1)).postNewPerson();
  }
  
  @Test
  public void updateAPersonServiceTest() {
    personService.updateAPerson();
    verify (iPersonDao,times(1)).updateAPerson();
  }
  
  @Test
  public void deleteAPersonServiceTest() {
    personService.deleteAPerson();
    verify (iPersonDao,times(1)).deleteAPerson();
  }
}
