package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingHome;
import com.safetynet.alerts.model.WorkingResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkingHomeServiceImplTest {


  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  @Mock
  RetrieveWorkingDataRepository retrieveWorkingDataRepository;

  @InjectMocks
  WorkingHomeServiceImpl workingHomeService;

  ArrayList<WorkingHome> workingHomes = new ArrayList<>();

  WorkingHome workingHome = new WorkingHome();
  WorkingHome workingHome2 = new WorkingHome();
  OriginalResponse originalResponse = new OriginalResponse();
  OriginalPerson originalPerson = new OriginalPerson();
  OriginalPerson originalPerson2 = new OriginalPerson();
  HashSet<WorkingHome> workingHomeHashSet = new HashSet<>();
  WorkingResponse workingResponse = new WorkingResponse();



  @BeforeEach
  void setUp() {
    workingHome.setIdHome(UUID.randomUUID());
    workingHome.setAddress("15 rue des prés");
    workingHome.setCity("Toulouse");
    workingHome.setZip("31100");

    workingHome2.setIdHome(UUID.randomUUID());
    workingHome2.setAddress("5bis avenue foch");
    workingHome2.setCity("Rochefort");
    workingHome2.setZip("17300");

    ArrayList<OriginalPerson> originalPersons = new ArrayList<>();
    originalPerson.setFirstName("Geff");
    originalPerson.setLastName("mwa");
    originalPerson.setAddress("15 rue de paris");
    originalPerson.setCity("Orange");
    originalPerson.setZip("J6T5A6");
    originalPerson.setEmail("toto@toto.fr");
    originalPerson.setPhone("0619674945");
    originalPersons.add(originalPerson);

    originalPerson2.setFirstName("Hubert");
    originalPerson2.setLastName("trotro");
    originalPerson2.setAddress("1bis rue des eperviers");
    originalPerson2.setCity("Paris");
    originalPerson2.setZip("75000");
    originalPerson2.setEmail("wesg@toto.fr");
    originalPerson2.setPhone("0619688945");
    originalPersons.add(originalPerson2);

originalResponse.setPersons(originalPersons);
    workingHomes.add(workingHome);
    workingHomes.add(workingHome2);
    workingHomeHashSet.addAll(workingHomes);

    workingResponse.setHomes(workingHomes);

  }


  @Test
  void searchWorkingHome() {
assertEquals(workingHome.getAddress(), workingHomeService.searchWorkingHome("15 rue des prés",
        workingHomes).getAddress());
    assertEquals(workingHome.getIdHome(), workingHomeService.searchWorkingHome("15 rue des prés",
            workingHomes).getIdHome());
    assertEquals(workingHome.getCity(), workingHomeService.searchWorkingHome("15 rue des prés",
            workingHomes).getCity());
    assertEquals(workingHome.getZip(), workingHomeService.searchWorkingHome("15 rue des prés",
            workingHomes).getZip());
  }

  @Test
  void getWorkingHomesArrayList() {

    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertNotNull(workingHomeService.getWorkingHomesArrayList());
    assertEquals(originalPerson.getAddress(),
            workingHomeService.getWorkingHomesArrayList().get(0).getAddress());
    assertEquals(originalPerson.getZip(),
            workingHomeService.getWorkingHomesArrayList().get(0).getZip());
    assertEquals(originalPerson.getCity(),
            workingHomeService.getWorkingHomesArrayList().get(0).getCity());
    assertEquals(originalPerson2.getAddress(),
            workingHomeService.getWorkingHomesArrayList().get(1).getAddress());
    assertEquals(originalPerson2.getZip(),
            workingHomeService.getWorkingHomesArrayList().get(1).getZip());
    assertEquals(originalPerson2.getCity(),
            workingHomeService.getWorkingHomesArrayList().get(1).getCity());
  }

  @Test
  void createWorkingHomes() {
    assertDoesNotThrow(()->retrieveOriginalDataRepository);
  }

  @Test
  void getFinishedWorkingHomesHashMap() {

    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertNotNull(workingHomeService.getFinishedWorkingHomesHashMap().values().toString());
  }

  @Test
  void getUnFinishedWorkingHomesHashMap() {
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertNotNull(workingHomeService.getUnFinishedWorkingHomesHashMap().values().toString());
  }

  @Test
  void getHomeById() {
    when(retrieveWorkingDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE)).thenReturn(workingResponse);
    assertEquals(workingHome.getIdHome(),
            workingHomeService.getHomeById(workingHome.getIdHome()).getIdHome());
    assertEquals(workingHome.getAddress(),
            workingHomeService.getHomeById(workingHome.getIdHome()).getAddress());
    assertEquals(workingHome.getZip(),
            workingHomeService.getHomeById(workingHome.getIdHome()).getZip());
    assertEquals(workingHome.getCity(),
            workingHomeService.getHomeById(workingHome.getIdHome()).getCity());

    assertEquals(workingHome2.getIdHome(),
            workingHomeService.getHomeById(workingHome2.getIdHome()).getIdHome());
    assertEquals(workingHome2.getAddress(),
            workingHomeService.getHomeById(workingHome2.getIdHome()).getAddress());
    assertEquals(workingHome2.getZip(),
            workingHomeService.getHomeById(workingHome2.getIdHome()).getZip());
    assertEquals(workingHome2.getCity(),
            workingHomeService.getHomeById(workingHome2.getIdHome()).getCity());
  }

  @Test
  void retrieveWorkingHomeFromFile() {
    when(retrieveWorkingDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE)).thenReturn(workingResponse);
    assertNotNull(workingHomeService.retrieveWorkingHomeFromFile(FilesPath.WORKING_INPUT_FILE));
  }
}