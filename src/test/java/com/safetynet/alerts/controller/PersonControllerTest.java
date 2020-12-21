package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

  @MockBean
  OutPutPersonService outPutPersonService;
  @MockBean
  OutPutHomeService outPutHomeService;
  @MockBean
  OutPutMedicalRecordService outPutMedicalRecordService;
  @MockBean
  RetrieveOutPutResponseService retrieveOutPutResponseService;
  @MockBean
  RetrieveOriginalDataService retrieveOriginalDataService;
  @MockBean
  OriginalPersonsService originalPersonsService;
  @MockBean
  OriginalFleService originalFleService;
  @MockBean
  CreateWorkingFileService createWorkingFileService;
  @MockBean
  OriginalResponse originalResponse;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  OriginalPerson originalPerson;

  @MockBean
  OutPutPerson outPutPerson;

  @MockBean
  OutPutHome outPutHome;

  @MockBean
  OutPutChild outPutChild;

  @InjectMocks
  PersonController personController;


  @Test
  void deletePerson() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            delete("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));


  }

  @Test
  void deletePersonMissingParamInBody() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            delete("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));


  }

  @Test
  void deletePersonNoBody() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            delete("/person")
                    .contentType(MediaType.APPLICATION_JSON)

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 409));


  }

  @Test
  void postPersonExisting() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            post("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));


  }

  @Test
  void postPersonUnknown() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(false);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            post("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));


  }

  @Test
  void postPersonMissingParam() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(false);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            post("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));


  }

  @Test
  void postPersonNoBody() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(false);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            post("/person")
                    .contentType(MediaType.APPLICATION_JSON)

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 409));


  }

  @Test
  void putPerson() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            put("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));


  }

  @Test
  void putPersonUnknown() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(false);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            put("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"lastName\":\"Serre\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));


  }

  @Test
  void putPersonMissingParam() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(false);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            put("/person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));


  }

  @Test
  void putPersonMediaTypeNotSupported() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(false);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            put("/person")
                    .contentType(MediaType.APPLICATION_PDF)
                    .content(" { \r\n"
                            + "  \"firstName\":\"Geff\", \r\n"
                            + "  \"address\":\"04/14/1982\", \r\n"
                            + "  \"city\": \"Toulouse\", \r\n"
                            + "  \"zip\": \"31100\", \r\n"
                            + "  \"phone\": \"0561417769\", \r\n"
                            + "  \"email\": \"geff@goauld.fr\" \r\n"
                            + " }")
    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 406));


  }

  @Test
  void putPersonNoBody() throws Exception {
    when(originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(false);
    when(retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    when(originalPersonsService.getOriginalPersonByFirstAndLastName("Geff", "Serre",
            originalResponse.getPersons())).thenReturn(originalPerson);

    mockMvc.perform(
            put("/person")
                    .contentType(MediaType.APPLICATION_JSON)

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 409));


  }

  @Test
  void getEmailforACity() throws Exception {
    HashSet<UUID> idHomes = new HashSet<>();
    HashSet<String> output = new HashSet<>();
    String email = "toto@tt.gt";
    output.add(email);
    when(outPutHomeService.getHomesByCity("Toulouse")).thenReturn(idHomes);
    when(outPutPersonService.getPersonsEmailByCity(idHomes)).thenReturn(output);
    mockMvc.perform(
            get("/communityEmail")
                    .contentType("application/json")
                    .param("city", "Toulouse")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/communityEmail");
                        request.setQueryString("test communityEmail");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void getEmailforACity2() throws Exception {
    HashSet<UUID> idHomes = new HashSet<>();
    HashSet<String> output = new HashSet<>();
    String email = "toto@tt.gt";
    // output.add(email);
    when(outPutHomeService.getHomesByCity("Toulouse")).thenReturn(idHomes);
    when(outPutPersonService.getPersonsEmailByCity(idHomes)).thenReturn(output);
    mockMvc.perform(
            get("/communityEmail")
                    .contentType("application/json")
                    .param("city", "Toulouse")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/communityEmail");
                        request.setQueryString("test communityEmail");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

  @Test
  void getEmailforACityError() throws Exception {
    HashSet<UUID> idHomes = new HashSet<>();
    HashSet<String> output = new HashSet<>();
    String email = "toto@tt.gt";
    output.add(email);
    when(outPutHomeService.getHomesByCity("Toulouse")).thenReturn(idHomes);
    when(outPutPersonService.getPersonsEmailByCity(idHomes)).thenReturn(output);
    mockMvc.perform(
            get("/communityEmail")
                    .contentType("application/json")
                    .param("ville", "Toulouse")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/communityEmail");
                        request.setQueryString("test communityEmail");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));

  }

  @Test
  void getEmailforACityError2() throws Exception {
    HashSet<UUID> idHomes = new HashSet<>();
    HashSet<String> output = new HashSet<>();
    String email = "toto@tt.gt";
    output.add(email);
    when(outPutHomeService.getHomesByCity("Toulouse")).thenReturn(idHomes);
    when(outPutPersonService.getPersonsEmailByCity(idHomes)).thenReturn(output);
    mockMvc.perform(
            get("/communityEmail")
                    .contentType("application/json")

                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/communityEmail");
                        request.setQueryString("test communityEmail");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));

  }

  @Test
  void getPersonInfoNoFound() throws Exception {

    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    mockMvc.perform(
            get("/personInfo")
                    .contentType("application/json")
                    .param("firstName", "Geff")
                    .param("lastName", "Serre")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/personInfo");
                        request.setQueryString("test personInfo");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));

  }

  @Test
  void getPersonInfo() throws Exception {

    ArrayList<OutPutPerson> selectedPersons = new ArrayList<>();
    selectedPersons.add(outPutPerson);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    when(outPutPersonService.getPersonsByFirstAndLastName("Geff", "Serre")).thenReturn(selectedPersons);
    mockMvc.perform(
            get("/personInfo")
                    .contentType("application/json")
                    .param("firstName", "Geff")
                    .param("lastName", "Serre")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/personInfo");
                        request.setQueryString("test personInfo");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 200));

  }

  @Test
  void getChildsWrongParam() throws Exception {
    ArrayList<OutPutPerson> selectedPersons = new ArrayList<>();
    selectedPersons.add(outPutPerson);
    when(outPutPersonService.isPersonAlreadyInFile("Geff", "Serre")).thenReturn(true);
    when(outPutPersonService.getPersonsByFirstAndLastName("Geff", "Serre")).thenReturn(selectedPersons);
    mockMvc.perform(
            get("/childAlert")
                    .contentType("application/json")
                    .param("firstName", "Geff")
                    .param("lastName", "Serre")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/childAlert");
                        request.setQueryString("test childAlert");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 400));
  }

  @Test
  void getChildsNoResult() throws Exception {
    ArrayList<OutPutPerson> selectedPersons = new ArrayList<>();
    selectedPersons.add(outPutPerson);

    when(outPutHomeService.getHomeByAddress("Geff")).thenReturn(outPutHome);
    when(outPutPersonService.getPersonsByHomeID(outPutHome)).thenReturn(selectedPersons);
    when(outPutPersonService.getCountedTypeOfPersons(outPutHome.getPersons())).thenReturn(outPutChild);
    mockMvc.perform(
            get("/childAlert")
                    .contentType("application/json")
                    .param("address", "Geff")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/childAlert");
                        request.setQueryString("test childAlert");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));
  }

  @Test
  void getChilds() throws Exception {
    ArrayList<OutPutPerson> selectedPersons = new ArrayList<>();
    selectedPersons.add(outPutPerson);
    outPutChild.setChild(selectedPersons);

    when(outPutHomeService.getHomeByAddress("Geff")).thenReturn(outPutHome);
    when(outPutPersonService.getPersonsByHomeID(outPutHome)).thenReturn(selectedPersons);
    when(outPutPersonService.getCountedTypeOfPersons(outPutHome.getPersons())).thenReturn(outPutChild);
    mockMvc.perform(
            get("/childAlert")
                    .contentType("application/json")
                    .param("address", "Geff")
                    .with(new RequestPostProcessor() {
                      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                        request.setRemoteAddr("/childAlert");
                        request.setQueryString("test childAlert");
                        return request;
                      }
                    })

    )
            .andExpect(result -> assertTrue(result.getResponse().getStatus() == 204));
  }
}