package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkingMedicalRecordServiceImplTest {

  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @InjectMocks
  WorkingMedicalRecordServiceImpl workingMedicalRecordService;
  OriginalMedicalrecord originalMedicalrecord= new OriginalMedicalrecord();
  OriginalMedicalrecord originalMedicalrecord2= new OriginalMedicalrecord();
  OriginalMedicalrecord originalMedicalrecord3= new OriginalMedicalrecord();
  OriginalMedicalrecord originalMedicalrecord4= new OriginalMedicalrecord();
  ArrayList<OriginalMedicalrecord> originalMedicalRecords = new ArrayList<>();
  OriginalResponse originalResponse = new OriginalResponse();
  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  @BeforeEach
  void setUp() {
    ArrayList<String> medications = new ArrayList<>();
    medications.add("Seresta 10mg");
    ArrayList<String> allergies = new ArrayList<>();
    allergies.add("python");
    originalMedicalrecord.setFirstName("Geff");
    originalMedicalrecord.setLastName("Mwa");
    originalMedicalrecord.setBirthdate(LocalDate.parse("08/23/1984", dtf));
    originalMedicalrecord.setMedications(medications);
    originalMedicalrecord.setAllergies(allergies);

    ArrayList<String> medications2 = new ArrayList<>();
    medications2.add("Computer");
    ArrayList<String> allergies2 = new ArrayList<>();
    allergies2.add("people");
    originalMedicalrecord2.setFirstName("Sarah");
    originalMedicalrecord2.setLastName("twa");
    originalMedicalrecord2.setBirthdate(LocalDate.parse("01/02/1982", dtf));
    originalMedicalrecord2.setMedications(medications2);
    originalMedicalrecord2.setAllergies(allergies2);

    ArrayList<String> medications3 = new ArrayList<>();
    medications3.add("Java");
    ArrayList<String> allergies3 = new ArrayList<>();
    allergies3.add("python");
    originalMedicalrecord3.setFirstName("Edouard");
    originalMedicalrecord3.setLastName("Lui");
    originalMedicalrecord3.setBirthdate(LocalDate.parse("11/18/1990", dtf));
    originalMedicalrecord3.setMedications(medications3);
    originalMedicalrecord3.setAllergies(allergies3);

    ArrayList<String> medications4 = new ArrayList<>();
    medications.add("raoult");
    ArrayList<String> allergies4 = new ArrayList<>();
    allergies.add("covid");
    originalMedicalrecord4.setFirstName("Marie");
    originalMedicalrecord4.setLastName("Parla");
    originalMedicalrecord4.setBirthdate(LocalDate.parse("08/04/1978", dtf));
    originalMedicalrecord4.setMedications(medications4);
    originalMedicalrecord4.setAllergies(allergies4);
    originalMedicalRecords.add(originalMedicalrecord);
    originalMedicalRecords.add(originalMedicalrecord2);
    originalMedicalRecords.add(originalMedicalrecord3);
    originalResponse.setMedicalrecords(originalMedicalRecords);
  }

  @Test
  void getWorkingMedicalRecordsHashMap() {
    String resultKey1 =
            originalMedicalrecord.getFirstName()+ ","+originalMedicalrecord.getLastName();
    String resultKey2 =
            originalMedicalrecord2.getFirstName()+ ","+originalMedicalrecord2.getLastName();
    String resultKey3 =
            originalMedicalrecord3.getFirstName()+ ","+originalMedicalrecord3.getLastName();

    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertEquals(originalMedicalrecord.getMedications(),
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap().get(resultKey1).getMedications());
    assertEquals(originalMedicalrecord.getAllergies(),
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap().get(resultKey1).getAllergies());

    assertEquals(originalMedicalrecord2.getMedications(),
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap().get(resultKey2).getMedications());
    assertEquals(originalMedicalrecord2.getAllergies(),
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap().get(resultKey2).getAllergies());

    assertEquals(originalMedicalrecord3.getMedications(),
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap().get(resultKey3).getMedications());
    assertEquals(originalMedicalrecord3.getAllergies(),
            workingMedicalRecordService.getWorkingMedicalRecordsHashMap().get(resultKey3).getAllergies());
  }

}