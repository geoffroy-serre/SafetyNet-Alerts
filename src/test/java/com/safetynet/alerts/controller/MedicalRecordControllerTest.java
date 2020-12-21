package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.OutPutMedicalRecord;
import com.safetynet.alerts.services.CreateWorkingFileServiceImpl;
import com.safetynet.alerts.services.OriginalFileServiceImpl;
import com.safetynet.alerts.services.OriginalMedicalRecordServiceImpl;
import com.safetynet.alerts.utils.RequestLogger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.assertj.core.util.Files;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

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
  OriginalMedicalRecordService originalMedicalRecordService;
  @MockBean
  CreateWorkingFileService createWorkingFileService;
  @MockBean
  OriginalResponse originalResponse;
  @MockBean
  OriginalFirestation originalFirestation;
  @MockBean
  RequestLogger requestLogger;
  @MockBean
  OriginalMedicalrecord originalMedicalrecord2;

  @MockBean
  HttpServletRequest servletRequest;

  @MockBean
  HttpServletResponse servletResponse;
@Autowired
  private MockMvc mockMvc;

  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  @InjectMocks
  MedicalRecordController medicalRecordController;

  @BeforeEach
  void setUp() {
  }

  @Test
  void postMedicalRecordWithoutBody() throws Exception {
    mockMvc.perform(post("/medicalRecord"))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
  }

  @Test
  void postMedicalRecordWithMissingParam() throws Exception {
    OriginalMedicalrecord originalMedicalrecord2 = new OriginalMedicalrecord();
    originalMedicalrecord2.setLastName("Twa");
    mockMvc.perform(
            post("/medicalRecord")
                    .contentType("application/json")
                    .content("{\"lastName\" : \"" + originalMedicalrecord2.getLastName() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));
  }

  @Test
  void postMedicalRecord() throws Exception {

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalMedicalRecordService.isMedicalRecordAlreadyInFile("Geff","Mwa",
            originalResponse.getMedicalrecords())).thenReturn(false);
    mockMvc.perform(
            post("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            +"  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"birthdate\":\"04/14/1982\", \r\n"
                            + "  \"medications\": [\"Seretas\"], \r\n"
                            + "  \"allergies\": [\"Python\"] \r\n"
                            +" }")
               )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));
  }

  @Test
  void putMedicalRecord() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalMedicalRecordService.isMedicalRecordAlreadyInFile("Geff","Serre",
            originalResponse.getMedicalrecords())).thenReturn(true);
    mockMvc.perform(
            put("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            +"  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"birthdate\":\"04/14/1982\", \r\n"
                            + "  \"medications\": [\"Seretas\"], \r\n"
                            + "  \"allergies\": [\"Python\"] \r\n"
                            +" }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));


  }

  @Test
  void putMedicalRecordError() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalMedicalRecordService.isMedicalRecordAlreadyInFile("Geff","Serre",
            originalResponse.getMedicalrecords())).thenReturn(false);
    mockMvc.perform(
            put("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            +"  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"birthdate\":\"04/14/1982\", \r\n"
                            + "  \"medications\": [\"Seretas\"], \r\n"
                            + "  \"allergies\": [\"Python\"] \r\n"
                            +" }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));


  }

  @Test
  void deleteMedicalRecord() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalMedicalRecordService.isMedicalRecordAlreadyInFile("Geff","Serre",
            originalResponse.getMedicalrecords())).thenReturn(true);
    when(originalMedicalRecordService.getMedicalRecordByFirstLastName(originalResponse.getMedicalrecords(),"Geff",
            "Serre")).thenReturn(originalMedicalrecord2);
    mockMvc.perform(
            delete("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            +"  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"birthdate\":\"04/14/1982\", \r\n"
                            + "  \"medications\": [\"Seretas\"], \r\n"
                            + "  \"allergies\": [\"Python\"] \r\n"
                            +" }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));


  }

  @Test
  void deleteMedicalRecordUnknow() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalMedicalRecordService.isMedicalRecordAlreadyInFile("Geff","Serre",
            originalResponse.getMedicalrecords())).thenReturn(false);
    when(originalMedicalRecordService.getMedicalRecordByFirstLastName(originalResponse.getMedicalrecords(),"Geff",
            "Serre")).thenReturn(originalMedicalrecord2);
    mockMvc.perform(
            delete("/medicalRecord")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            +"  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"birthdate\":\"04/14/1982\", \r\n"
                            + "  \"medications\": [\"Seretas\"], \r\n"
                            + "  \"allergies\": [\"Python\"] \r\n"
                            +" }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));


  }
}