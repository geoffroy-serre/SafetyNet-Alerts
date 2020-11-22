package com.safetynet.alerts.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IHomeDao;
import com.safetynet.alerts.utils.WorkingFileOuput;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@WebMvcTest(value =HomeService.class)
public class HomeServiceTest {
  
  @Autowired
  HomeService homeService;
  
  @MockBean
  IHomeDao iHomeDao;
  
    
  @Test
  public void getHomeListServiceTest() {
    homeService.getHomeService(WorkingFileOuput.getWorkingInputFile());

    verify(iHomeDao, times(1)).getHomeListDao(WorkingFileOuput.getWorkingInputFile());
  }
  
}
