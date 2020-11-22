package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.services.HomeService;
import com.safetynet.alerts.utils.WorkingFileOuput;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = HomeController.class)
public class HomeControllerTest {

  @MockBean
  private HomeService homeService;

  @Autowired
  private HomeController homeController;

  @Test
  public void getHomeControllerTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {
    homeController.getHomeInfoController();
    verify(homeService, times(1)).getHomeService(WorkingFileOuput.getWorkingInputFile());
  }

  @Test
  public void JsonPArseExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(homeService.getHomeService(WorkingFileOuput.getWorkingInputFile())).thenThrow(JsonParseException.class);
    assertThatCode(() -> homeController.getHomeInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void JsonMappingExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(homeService.getHomeService(WorkingFileOuput.getWorkingInputFile())).thenThrow(JsonMappingException.class);
    assertThatCode(() -> homeController.getHomeInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void NoSuchFileExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(homeService.getHomeService(WorkingFileOuput.getWorkingInputFile())).thenThrow(NoSuchFileException.class);
    assertThatCode(() -> homeController.getHomeInfoController())
        .doesNotThrowAnyException();
  }

  @Test
  public void IoExceptionIsCatchedTest() throws JsonParseException,
      JsonMappingException, NoSuchFileException, IOException {

    when(homeService.getHomeService(WorkingFileOuput.getWorkingInputFile())).thenThrow(IOException.class);
    assertThatCode(() -> homeController.getHomeInfoController())
        .doesNotThrowAnyException();
  }
}
