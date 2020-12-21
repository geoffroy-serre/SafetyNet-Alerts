package com.safetynet.alerts.controller;

import com.safetynet.alerts.services.CreateWorkingFileServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = CreateWorkingFileController.class)
class CreateWorkingFileControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CreateWorkingFileServiceImpl createWorkingFileService;

  @InjectMocks
  CreateWorkingFileController createWorkingFileController;

  @Test
  void createWorkingFile() throws Exception {
    mockMvc.perform(
            get("/createWorkingFile")
                    .contentType("application/json")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/flood/stations");
                        request.setQueryString("test flood");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 201));

  }
}