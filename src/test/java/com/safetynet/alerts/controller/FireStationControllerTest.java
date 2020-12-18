package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.DeleteFirestation;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
class FireStationControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  OutPutHomeService outPutHomeService;
  @MockBean
  OutPutFireStationService outPutFireStationService;
  @MockBean
  OutPutPersonService outPutPersonService;
  @MockBean
  RetrieveOutPutResponseService retrieveOutPutResponseService;
  @MockBean
  OutPutMedicalRecordService outPutMedicalRecordService;
  @MockBean
  OriginalFleService originalFleService;
  @MockBean
  OriginalFireStationService originalFireStationService;
  @MockBean
  CreateWorkingFileService createWorkingFileService;
  @MockBean
  OriginalResponse originalResponse;
  @MockBean
  OriginalFirestation originalFirestation;


  @Test
  void deleteFireStationWithoutBody() throws Exception {
    mockMvc.perform(delete("/firestation")).andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
  }


}