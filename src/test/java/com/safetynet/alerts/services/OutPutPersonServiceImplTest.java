package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutMedicalRecord;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.OutPutResponse;
import com.safetynet.alerts.repository.RetrieveOutPutDataRepositoryImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
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
class OutPutPersonServiceImplTest {

  @Mock
  RetrieveOutPutDataRepositoryImpl retrieveOutPutDataRepository;

  @InjectMocks
  OutPutPersonServiceImpl outPutPersonService;

  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
OutPutResponse outPutResponse = new OutPutResponse();
  OutPutPerson outPutPerson = new OutPutPerson();
  OutPutPerson outPutPerson2 = new OutPutPerson();
  OutPutPerson outPutPerson3 = new OutPutPerson();
  OutPutPerson outPutPerson4 = new OutPutPerson();
  OutPutHome outPutHome = new OutPutHome();
  OutPutHome outPutHome2 = new OutPutHome();
  ArrayList<OutPutPerson> outPutPersons = new ArrayList<>();
  ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
  ArrayList<UUID> uuids = new ArrayList<>();
  OutPutMedicalRecord outPutMedicalRecord = new OutPutMedicalRecord();
  OutPutMedicalRecord outPutMedicalRecord2 = new OutPutMedicalRecord();
  ArrayList<String> allergies = new ArrayList<>();
  ArrayList<String> medications = new ArrayList<>();
  ArrayList<OutPutMedicalRecord> outPutMedicalRecords = new ArrayList<>();
  HashSet<UUID> uuidHashSet = new HashSet<>();

  @BeforeEach
  void setUp() {

    outPutPerson.setBirthdate(LocalDate.parse("08/23/1984", dtf));
    outPutPerson.setFirstName("Geff");
    outPutPerson.setLastName("mwa");
    outPutPerson.setEmail("toto@tata.fr");
    outPutPerson.setPhone("0619674995");
    outPutPerson.setIdHome(UUID.randomUUID());
    outPutPersons.add(outPutPerson);
uuids.add(outPutPerson.getIdHome());
outPutPerson.setIdMedicalRecord(UUID.randomUUID());
outPutMedicalRecord.setIdMedicalRecord(outPutPerson.getIdMedicalRecord());
    ArrayList<String> allergies = new ArrayList<>();
    ArrayList<String> medications = new ArrayList<>();
    medications.add("Java");
    allergies.add("Python");
outPutMedicalRecord.setMedications(medications);
outPutMedicalRecord.setAllergies(allergies);

    outPutPerson2.setBirthdate(LocalDate.parse("08/23/2012", dtf));
    outPutPerson2.setFirstName("Geff");
    outPutPerson2.setLastName("twa");
    outPutPerson2.setEmail("tata@tata.fr");
    outPutPerson2.setPhone("0685490014");
    outPutPerson2.setIdHome(UUID.randomUUID());
    outPutPersons.add(outPutPerson2);
    uuids.add(outPutPerson2.getIdHome());
    outPutPerson2.setIdMedicalRecord(UUID.randomUUID());
    outPutMedicalRecord2.setIdMedicalRecord(outPutPerson2.getIdMedicalRecord());
    ArrayList<String> allergies2 = new ArrayList<>();
    ArrayList<String> medications2 = new ArrayList<>();
    medications2.add("Computer");
    allergies2.add("People");
    outPutMedicalRecord2.setMedications(medications2);
    outPutMedicalRecord2.setAllergies(allergies2);


    outPutPerson3.setBirthdate(LocalDate.parse("08/23/2000", dtf));
    outPutPerson4.setBirthdate(LocalDate.parse("08/23/2012", dtf));
    outPutPerson3.setFirstName("Geff");
    outPutPerson3.setLastName("lui");
    outPutPerson4.setFirstName("Geff");
    outPutPerson4.setLastName("toi");
    outPutPerson3.setIdHome(UUID.randomUUID());
    outPutPerson4.setIdHome(UUID.randomUUID());
    uuids.add(outPutPerson3.getIdHome());
    uuids.add(outPutPerson4.getIdHome());
outPutResponse.setPersons(outPutPersons);

outPutMedicalRecords.add(outPutMedicalRecord);
    outPutMedicalRecords.add(outPutMedicalRecord2);
    outPutHome.setStationNumber(10);
    outPutHome.setIdHome(outPutPerson.getIdHome());
    outPutHome.setAddress("8 avenue foch");
    outPutHome.setCity("Aytré");
    outPutHome.setZip("17300");

    outPutHome2.setStationNumber(10);
    outPutHome2.setIdHome(outPutPerson2.getIdHome());
    outPutHome2.setAddress("8 avenue foch");
    outPutHome2.setCity("Aytré");
    outPutHome2.setZip("17300");

    outPutHomes.add(outPutHome);
    outPutHomes.add(outPutHome2);

    uuidHashSet.addAll(uuids);

  }

  @Test
  void isPersonAlreadyInFile() {
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertTrue(outPutPersonService.isPersonAlreadyInFile("Geff","twa"));
    assertFalse(outPutPersonService.isPersonAlreadyInFile("Geff","trotro"));

  }

  @Test
  void setEmailToNull() {
assertEquals(null,outPutPersonService.setEmailToNull(outPutPersons).get(0).getEmail());
    assertEquals("mwa",outPutPersonService.setEmailToNull(outPutPersons).get(0).getLastName());
    assertEquals(null,outPutPersonService.setEmailToNull(outPutPersons).get(1).getEmail());
    assertEquals("twa",outPutPersonService.setEmailToNull(outPutPersons).get(1).getLastName());
  }

  @Test
  void getPersonByHomeIds() {
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertEquals(outPutPerson.getIdHome(), outPutPersonService.getPersonByHomeIds(uuids).get(0).getIdHome());
    assertEquals(outPutPerson2.getIdHome(),
            outPutPersonService.getPersonByHomeIds(uuids).get(1).getIdHome());
    assertTrue(outPutPersonService.getPersonByHomeIds(uuids).size()==2);
  }

  @Test
  void getPersonsPhones() {
    ArrayList<String> phoneNumbers = new ArrayList<>();
    phoneNumbers.add(outPutPerson.getPhone());
    phoneNumbers.add(outPutPerson2.getPhone());
    assertEquals(phoneNumbers.toString(),outPutPersonService.getPersonsPhones(outPutPersons).toString());


  }

  @Test
  void setPersonsHome() {
    assertNotNull(outPutPersonService.setPersonsHome(outPutPersons,outPutHomes));
    assertEquals(outPutPerson.getIdHome(),outPutPersonService.setPersonsHome(outPutPersons,
            outPutHomes).get(0).getIdHome());
    assertEquals(outPutPerson2.getIdHome(),outPutPersonService.setPersonsHome(outPutPersons,
            outPutHomes).get(1).getIdHome());
    assertEquals(outPutPerson.getFirstName(),outPutPersonService.setPersonsHome(outPutPersons,
            outPutHomes).get(0).getFirstName());
    assertEquals(outPutPerson.getLastName(),outPutPersonService.setPersonsHome(outPutPersons,
            outPutHomes).get(0).getLastName());
    assertEquals(outPutPerson2.getFirstName(),outPutPersonService.setPersonsHome(outPutPersons,
            outPutHomes).get(1).getFirstName());
    assertEquals(outPutPerson2.getLastName(),outPutPersonService.setPersonsHome(outPutPersons,
            outPutHomes).get(1).getLastName());

  }

  @Test
  void setMedicalRecordForPersons() {
    assertEquals(outPutPerson.getIdMedicalRecord(),
            outPutPersonService.setMedicalRecordForPersons(outPutPersons, outPutMedicalRecords).get(0).getIdMedicalRecord());
    assertEquals(outPutPerson2.getIdMedicalRecord(),
            outPutPersonService.setMedicalRecordForPersons(outPutPersons, outPutMedicalRecords).get(1).getIdMedicalRecord());
  }

  @Test
  void setPhoneNull() {
    assertEquals(null, outPutPersonService.setPhoneNull(outPutPersons).get(0).getPhone());
    assertEquals(null, outPutPersonService.setPhoneNull(outPutPersons).get(1).getPhone());
  }

  @Test
  void getPersonsByFirstAndLastName() {
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertEquals(outPutPerson.getFirstName(), outPutPersonService.getPersonsByFirstAndLastName(
            "geff", "mwa").get(0).getFirstName());
    assertEquals(outPutPerson.getLastName(), outPutPersonService.getPersonsByFirstAndLastName(
            "geff", "mwa").get(0).getLastName());

    assertEquals(outPutPerson2.getFirstName(), outPutPersonService.getPersonsByFirstAndLastName(
            "geff", "twa").get(0).getFirstName());
    assertEquals(outPutPerson2.getLastName(), outPutPersonService.getPersonsByFirstAndLastName(
            "geff", "twa").get(0).getLastName());
  }

  @Test
  void getPersonsEmailByCity() {
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    String emails = "[tata@tata.fr, toto@tata.fr]";
    assertEquals(emails, outPutPersonService.getPersonsEmailByCity(uuidHashSet).toString());
  }

  @Test
  void getCountedTypeOfPersons() {
    assertNotNull(outPutPersonService.getCountedTypeOfPersons(outPutPersons));
    assertNotNull(outPutPersonService.getCountedTypeOfPersons(outPutPersons).getChild());
    assertNotNull(outPutPersonService.getCountedTypeOfPersons(outPutPersons).getFamilly());
  }

  @Test
  void getPersonsByHomeID() {

    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertEquals(outPutPerson.getFirstName(),
            outPutPersonService.getPersonsByHomeID(outPutHome).get(0).getFirstName());
    assertEquals(outPutPerson.getLastName(),
            outPutPersonService.getPersonsByHomeID(outPutHome).get(0).getLastName());
    assertEquals(outPutPerson2.getFirstName(),
            outPutPersonService.getPersonsByHomeID(outPutHome2).get(0).getFirstName());
    assertEquals(outPutPerson2.getLastName(),
            outPutPersonService.getPersonsByHomeID(outPutHome2).get(0).getLastName());

  }

  @Test
  void getAllPerson() {
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertNotNull(outPutPersonService.getAllPerson());
    assertFalse(outPutPersonService.getAllPerson().isEmpty());

  }

}