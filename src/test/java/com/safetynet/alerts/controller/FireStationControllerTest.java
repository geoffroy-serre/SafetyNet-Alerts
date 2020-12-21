package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.utils.RequestLogger;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
  @MockBean
  RequestLogger requestLogger;
  @MockBean
  HttpServletRequest servletRequest;

  @MockBean
  HttpServletResponse servletResponse;


  @InjectMocks
  FireStationController fireStationController;

  @Test
  void deleteFireStationWithoutBody() throws Exception {
    mockMvc.perform(delete("/firestation")).andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
  }

  @Test
  void deleteFireStationUnknown() throws Exception {
    DeleteFirestation deleteFireStation = new DeleteFirestation();
    deleteFireStation.setStation(15);

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(false);

    mockMvc.perform(
            delete("/firestation")
                    .contentType("application/json")
                    .content("{\"station\" : \"" + deleteFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));
  }

  @Test
  void deleteFireStationknown2() throws Exception {
    DeleteFirestation deleteFireStation = new DeleteFirestation();
    deleteFireStation.setStation(15);
    deleteFireStation.setAddress("15 rue des trucs");
    when(originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getAddress(),
            originalResponse.getFirestations())).thenReturn(false);
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);

    mockMvc.perform(
            delete("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + deleteFireStation.getAddress() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));
  }

  @Test
  void deleteFireStationStation() throws Exception {
    DeleteFirestation deleteFireStation = new DeleteFirestation();
    deleteFireStation.setStation(15);
    deleteFireStation.setAddress("15 rue des trucs");
    when(originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(true);
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);

    mockMvc.perform(
            delete("/firestation")
                    .contentType("application/json")
                    .content("{\"station\" : \"" + deleteFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));
  }

  @Test
  void deleteFireStationStationAdress() throws Exception {
    DeleteFirestation deleteFireStation = new DeleteFirestation();
    deleteFireStation.setStation(15);
    deleteFireStation.setAddress("15 rue des trucs");
    when(originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getAddress(),
            originalResponse.getFirestations())).thenReturn(true);
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);

    mockMvc.perform(
            delete("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + deleteFireStation.getAddress() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));
  }

  @Test
  void postFireStationWithoutBody() throws Exception {
    mockMvc.perform(post("/firestation")).andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
  }

  @Test
  void postNewFireStationIncomplete() throws Exception {
    OriginalFirestation newFireStation = new OriginalFirestation();
    newFireStation.setStation(15);

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(newFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(false);

    mockMvc.perform(
            post("/firestation")
                    .contentType("application/json")
                    .content("{\"station\" : \"" + newFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
  }

  @Test
  void postNewFireStationIncomplete2() throws Exception {
    OriginalFirestation newFireStation = new OriginalFirestation();
    newFireStation.setStation(15);
    newFireStation.setAddress("15 rue des prés");

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(newFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(false);

    mockMvc.perform(
            post("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + newFireStation.getAddress() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
  }

  @Test
  void postNewFireStation() throws Exception {
    OriginalFirestation newFireStation = new OriginalFirestation();
    newFireStation.setStation(15);
    newFireStation.setAddress("15 rue des prés");

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(newFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(false);

    mockMvc.perform(
            post("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + newFireStation.getAddress() + "\","
                            + "\"station\" : \"" + newFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 500));

  }
  @Test
  void postNewFireStation2() throws Exception {
    OriginalFirestation newFireStation = new OriginalFirestation();
    newFireStation.setStation(15);
    newFireStation.setAddress("15 rue des prés");
  ArrayList<OriginalFirestation> originalFirestations = new ArrayList<>();
  originalFirestations.add(newFireStation);
  originalResponse.setFirestations(originalFirestations);

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalResponse.getFirestations()).thenReturn(originalFirestations);
    when(originalFireStationService.isFireStationAlreadyInFile(newFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(false);

    mockMvc.perform(
            post("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + newFireStation.getAddress() + "\","
                            + "\"station\" : \"" + newFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void postExistingFireStation() throws Exception {
    OriginalFirestation newFireStation = new OriginalFirestation();
    newFireStation.setStation(15);
    newFireStation.setAddress("15 rue des prés");

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn
            (originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(newFireStation.getStation(),
            newFireStation.getAddress(),
            originalResponse.getFirestations())).thenReturn(true);

    mockMvc.perform(
            post("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + newFireStation.getAddress() + "\","
                            + "\"station\" : \"" + newFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));

  }

  @Test
  void putFirestation() throws Exception {
    OriginalFirestation modifyFireStation = new OriginalFirestation();
    modifyFireStation.setStation(15);
    modifyFireStation.setAddress("15 rue des prés");

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn
            (originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(modifyFireStation.getStation(),
            modifyFireStation.getAddress(),
            originalResponse.getFirestations())).thenReturn(true);
    when(originalFireStationService.isAdressLinked(
            modifyFireStation.getAddress(), originalResponse.getFirestations())).thenReturn(true);

    mockMvc.perform(
            put("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + modifyFireStation.getAddress() + "\","
                            + "\"station\" : \"" + modifyFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void putFirestationFail() throws Exception {
    OriginalFirestation modifyFireStation = new OriginalFirestation();
    modifyFireStation.setStation(15);
    modifyFireStation.setAddress("15 rue des prés");

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn
            (originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(modifyFireStation.getStation(),
            modifyFireStation.getAddress(),
            originalResponse.getFirestations())).thenReturn(true);

    mockMvc.perform(
            put("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + modifyFireStation.getAddress() + "\","
                            + "\"station\" : \"" + modifyFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));

  }


  @Test
  void putFirestationNotPresent() throws Exception {
    OriginalFirestation modifyFireStation = new OriginalFirestation();
    modifyFireStation.setStation(15);
    modifyFireStation.setAddress("15 rue des prés");

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn
            (originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(modifyFireStation.getStation(),
            modifyFireStation.getAddress(),
            originalResponse.getFirestations())).thenReturn(false);

    mockMvc.perform(
            put("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + modifyFireStation.getAddress() + "\","
                            + "\"station\" : \"" + modifyFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));

  }

  @Test
  void putFireStationIncomplete() throws Exception {
    DeleteFirestation deleteFireStation = new DeleteFirestation();
    deleteFireStation.setStation(15);

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(false);

    mockMvc.perform(
            put("/firestation")
                    .contentType("application/json")
                    .content("{\"station\" : \"" + deleteFireStation.getStation() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
  }

  @Test
  void putFireStationIncomplete2() throws Exception {
    DeleteFirestation deleteFireStation = new DeleteFirestation();
    deleteFireStation.setAddress("15 rue des pres");

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getStation(),
            originalResponse.getFirestations())).thenReturn(false);


    mockMvc.perform(
            put("/firestation")
                    .contentType("application/json")
                    .content("{\"address\" : \"" + deleteFireStation.getAddress() + "\"}")

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
  }

  @Test
  void floodTest() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutFireStationService.setHomes(outPutFireStationService.getFiresStations(),
            outPutHomeService.getOutPutHomeList())).thenReturn(outPutFireStations);
    when(servletResponse.getStatus()).thenReturn(200);

    mockMvc.perform(
            get("/flood/stations")
                    .contentType("application/json")
                    .param("stations", "2")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/flood/stations");
                        request.setQueryString("test flood");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void floodTestTypeMismatch() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutFireStationService.setHomes(outPutFireStationService.getFiresStations(),
            outPutHomeService.getOutPutHomeList())).thenReturn(outPutFireStations);
    when(servletResponse.getStatus()).thenReturn(200);

    mockMvc.perform(
            get("/flood/stations")
                    .contentType("application/json")
                    .param("stations", "qqch")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/flood/stations");
                        request.setQueryString("test flood");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));

  }

  @Test
  void floodTestNoStation() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    mockMvc.perform(
            get("/flood/stations")
                    .contentType("application/json")
                    .param("stations", "2")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/flood/stations");
                        request.setQueryString("test flood");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

  @Test
  void fireTest() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);
    OutPutHome outPutHome = new OutPutHome();
    outPutHome.setAddress("15 rue des prés");
    outPutHome.setIdHome(UUID.randomUUID());
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutHomeService.getHomeByAddress("15 rue des prés")).thenReturn(outPutHome);
    when(servletResponse.getStatus()).thenReturn(200);

    mockMvc.perform(
            get("/fire")
                    .contentType("application/json")
                    .param("address", "15 rue des prés")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/fire");
                        request.setQueryString("test fire");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void fireTestNoResult() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);
    OutPutHome outPutHome = new OutPutHome();
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutHomeService.getHomeByAddress("15 rue des prés")).thenReturn(outPutHome);

    mockMvc.perform(
            get("/fire")
                    .contentType("application/json")
                    .param("address", "15 rue des prés")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/fire");
                        request.setQueryString("test fire");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

  @Test
  void fireTestNoResult2() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);
    OutPutHome outPutHome = new OutPutHome();
    outPutHome.setIdHome(UUID.randomUUID());
    outPutHome.setAddress("15 rue des prés");
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutHomeService.getHomeByAddress("18 rue des prés")).thenReturn(outPutHome);

    mockMvc.perform(
            get("/fire")
                    .contentType("application/json")
                    .param("address", "18 rue des prés")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/fire");
                        request.setQueryString("test fire");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

  @Test
  void getPhoneNumberByStationsNoResult() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);
    OutPutHome outPutHome = new OutPutHome();
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutHomeService.getHomeByAddress("15 rue des prés")).thenReturn(outPutHome);

    mockMvc.perform(
            get("/phoneAlert")
                    .contentType("application/json")
                    .param("firestation", "1")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/phoneAlert");
                        request.setQueryString("test phoneAlert");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

  @Test
  void getPhoneNumberByStation() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);

    OutPutHome outPutHome = new OutPutHome();
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),1))
            .thenReturn(outputFirestation);
    when(outPutHomeService.getHomeByAddress("15 rue des prés")).thenReturn(outPutHome);

    mockMvc.perform(
            get("/phoneAlert")
                    .contentType("application/json")
                    .param("firestation", "1")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/firestation");
                        request.setQueryString("test firestation");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void getPersonbyStationWithFamillyStats() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);

    OutPutHome outPutHome = new OutPutHome();
    ArrayList<OutPutHome> outputHomes = new ArrayList<>();
    outputHomes.add(outPutHome);
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),1))
            .thenReturn(outputFirestation);
    when(outPutHomeService.getHomeByStationNumber(outPutHomeService.getOutPutHomeList(),
            outputFirestation)).thenReturn(outputHomes);

    mockMvc.perform(
            get("/firestation")
                    .contentType("application/json")
                    .param("stationNumber", "1")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/firestation");
                        request.setQueryString("test firestation");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void getPersonbyStationWithFamillyStats2() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    outPutFireStations.add(outputFirestation);

    OutPutHome outPutHome = new OutPutHome();
    ArrayList<OutPutHome> outputHomes = new ArrayList<>();
    //outputHomes.add(outPutHome);
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),1))
            .thenReturn(outputFirestation);
    when(outPutHomeService.getHomeByStationNumber(outPutHomeService.getOutPutHomeList(),
            outputFirestation)).thenReturn(outputHomes);

    mockMvc.perform(
            get("/firestation")
                    .contentType("application/json")
                    .param("stationNumber", "1")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/firestation");
                        request.setQueryString("test firestation");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

  @Test
  void getPersonbyStationWithFamillyStatsNoResult() throws Exception {
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    OutPutFireStation outputFirestation = new OutPutFireStation();
    ArrayList<OutPutHome> outputHomes = new ArrayList<>();

    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),1))
            .thenReturn(null);
    when(outPutHomeService.getHomeByStationNumber(outPutHomeService.getOutPutHomeList(),
            outputFirestation)).thenReturn(outputHomes);

    mockMvc.perform(
            get("/firestation")
                    .contentType("application/json")
                    .param("stationNumber", "1")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/firestation");
                        request.setQueryString("test firestation");
                        return request;}})

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

}