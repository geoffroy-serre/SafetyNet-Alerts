package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingPerson;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkingPersonsServiceImplTest {

  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @InjectMocks
  WorkingPersonsServiceImpl workingPersonsService;

  ArrayList<OriginalPerson> originalPersons = new ArrayList<>();
  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
 OriginalPerson originalPerson = new OriginalPerson();
  OriginalPerson originalPerson2 = new OriginalPerson();
  OriginalPerson originalPerson3 = new OriginalPerson();
  OriginalResponse originalResponse = new OriginalResponse();
  WorkingPerson workingPerson = new WorkingPerson();
  ArrayList<WorkingPerson> workingPersons = new ArrayList<>();

  @BeforeEach
  void setUp() {
    workingPerson.setFirstName("geff");
    workingPerson.setLastName("serre");
    workingPerson.setEmail("toto@toto.fr");
    workingPerson.setPhone("0619674945");
    workingPersons.add(workingPerson);



    originalPerson.setFirstName("Geff");
    originalPerson.setLastName("Serre");
    originalPerson.setEmail("toto@toto.fr");
    originalPerson.setPhone("0619674945");
    originalPerson.setZip("17300");
    originalPerson.setCity("Rochefort");
    originalPerson.setAddress("18 rue Lesson");

    originalPerson2.setFirstName("Edouard");
    originalPerson2.setLastName("Moist");
    originalPerson2.setEmail("tata@toto.fr");
    originalPerson2.setPhone("0614874945");
    originalPerson2.setZip("09100");
    originalPerson2.setCity("Foix");
    originalPerson2.setAddress("54 rue du vivier");

    originalPerson3.setFirstName("Aurora");
    originalPerson3.setLastName("Deparla");
    originalPerson3.setEmail("titi@toto.fr");
    originalPerson3.setPhone("0619674005");
    originalPerson3.setZip("58000");
    originalPerson3.setCity("Nancy");
    originalPerson3.setAddress("2 avenue toufaire");

    originalPersons.add(originalPerson);
    originalPersons.add(originalPerson2);
    originalPersons.add(originalPerson3);



  }

  @Test
  void reestablishCase() {
    assertEquals("Geff", workingPersonsService.reestablishCase(workingPersons).get(0).getFirstName());
    assertEquals("Serre",
            workingPersonsService.reestablishCase(workingPersons).get(0).getLastName());

  }

  @Test
  void getWorkingPersonsHashMap() {
    String createdKey =
            originalPerson.getFirstName() + "," + originalPerson.getLastName() + "," + originalPerson.getAddress() + "," + originalPerson.getCity() + "," + originalPerson.getZip();
    originalResponse.setPersons(originalPersons);
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertEquals(originalPerson.getFirstName(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getFirstName());
    assertEquals(originalPerson.getLastName(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getLastName());
    assertEquals(originalPerson.getEmail(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getEmail());
    assertEquals(originalPerson.getPhone(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getPhone());

  }
  @Test
  void getWorkingPersonsHashMap2() {
    String createdKey =
            originalPerson2.getFirstName() + "," + originalPerson2.getLastName() + "," + originalPerson2.getAddress() + "," + originalPerson2.getCity() + "," + originalPerson2.getZip();
    originalResponse.setPersons(originalPersons);
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertEquals(originalPerson2.getFirstName(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getFirstName());
    assertEquals(originalPerson2.getLastName(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getLastName());
    assertEquals(originalPerson2.getEmail(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getEmail());
    assertEquals(originalPerson2.getPhone(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getPhone());

  }
  @Test
  void getWorkingPersonsHashMap3() {
    String createdKey =
            originalPerson3.getFirstName() + "," + originalPerson3.getLastName() + "," + originalPerson3.getAddress() + "," + originalPerson3.getCity() + "," + originalPerson3.getZip();
    originalResponse.setPersons(originalPersons);
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertEquals(originalPerson3.getFirstName(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getFirstName());
    assertEquals(originalPerson3.getLastName(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getLastName());
    assertEquals(originalPerson3.getEmail(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getEmail());
    assertEquals(originalPerson3.getPhone(),
            workingPersonsService.getWorkingPersonsHashMap().get(createdKey).getPhone());

  }
}