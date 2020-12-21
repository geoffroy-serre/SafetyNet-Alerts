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
class OriginalMedicalRecordServiceImplTest {


  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  @InjectMocks
  OriginalMedicalRecordServiceImpl originalMedicalRecordService;
  private OriginalMedicalrecord originalMedicalrecord = new OriginalMedicalrecord();
  private OriginalMedicalrecord originalMedicalrecord2 = new OriginalMedicalrecord();
  private OriginalMedicalrecord originalMedicalrecord3 = new OriginalMedicalrecord();
  private OriginalMedicalrecord originalMedicalrecord4 = new OriginalMedicalrecord();
  private ArrayList<OriginalMedicalrecord> originalMedicalRecords = new ArrayList<>();
  private OriginalResponse originalResponse = new OriginalResponse();


  @BeforeEach
  void setup() {
    ArrayList<String> medications = new ArrayList<>();
    medications.add("Seresta 10mg");
    ArrayList<String> allergies = new ArrayList<>();
    allergies.add("python");
    originalMedicalrecord.setFirstName("Geff");
    originalMedicalrecord.setLastName("Mwa");
    originalMedicalrecord.setBirthdate(LocalDate.parse("08/23/1984", dtf));
    originalMedicalrecord.setMedications(medications);
    originalMedicalrecord.setAllergies(allergies);
    originalMedicalrecord2.setFirstName("Sarah");
    originalMedicalrecord2.setLastName("twa");
    originalMedicalrecord2.setBirthdate(LocalDate.parse("01/02/1982", dtf));
    originalMedicalrecord2.setMedications(medications);
    originalMedicalrecord2.setAllergies(allergies);
    originalMedicalrecord3.setFirstName("Edouard");
    originalMedicalrecord3.setLastName("Lui");
    originalMedicalrecord3.setBirthdate(LocalDate.parse("11/18/1990", dtf));
    originalMedicalrecord3.setMedications(medications);
    originalMedicalrecord3.setAllergies(allergies);
    originalMedicalrecord4.setFirstName("Marie");
    originalMedicalrecord4.setLastName("Parla");
    originalMedicalrecord4.setBirthdate(LocalDate.parse("08/04/1978", dtf));
    originalMedicalrecord4.setMedications(medications);
    originalMedicalrecord4.setAllergies(allergies);
    originalMedicalRecords.add(originalMedicalrecord);
    originalMedicalRecords.add(originalMedicalrecord2);
    originalMedicalRecords.add(originalMedicalrecord3);
    originalResponse.setMedicalrecords(originalMedicalRecords);
  }

  @Test
  void postNewMedicalRecordExisting() {
    String result = "[MedicalrecordsItem{allergies = '[python]',firstName = 'Geff',lastName = " +
            "'Mwa',birthdate = '1984-08-23',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Sarah',lastName = 'twa'," +
            "birthdate = '1982-01-02',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Edouard',lastName = 'Lui'," +
            "birthdate = '1990-11-18',medications = '[Seresta 10mg]'}]";
    assertEquals(result, originalMedicalRecordService.postNewMedicalRecord(originalMedicalrecord,
            originalMedicalRecords).toString());

  }

  @Test
  void postNewMedicalRecord() {
    String result = "[MedicalrecordsItem{allergies = '[python]',firstName = 'Geff'," +
            "lastName = 'Mwa',birthdate = '1984-08-23',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Sarah',lastName = 'twa'," +
            "birthdate = '1982-01-02',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Edouard',lastName = 'Lui'," +
            "birthdate = '1990-11-18',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Marie',lastName = 'Parla'," +
            "birthdate = '1978-08-04',medications = '[Seresta 10mg]'}]";
    assertEquals(result, originalMedicalRecordService.postNewMedicalRecord(originalMedicalrecord4,
            originalMedicalRecords).toString());

  }

  @Test
  void deleteOriginalMedicalRecordExisting() {
    String result = "[MedicalrecordsItem{allergies = '[python]',firstName = 'Sarah',lastName = " +
            "'twa'," +
            "birthdate = '1982-01-02',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Edouard',lastName = 'Lui'," +
            "birthdate = '1990-11-18',medications = '[Seresta 10mg]'}]";
    assertEquals(result,
            originalMedicalRecordService.deleteOriginalMedicalRecord(originalMedicalrecord,
                    originalMedicalRecords).toString());
  }

  @Test
  void deleteOriginalMedicalRecordNotExisting() {
    String result = "[MedicalrecordsItem{allergies = '[python]',firstName = 'Geff'," +
            "lastName = 'Mwa',birthdate = '1984-08-23',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Sarah',lastName = 'twa'," +
            "birthdate = '1982-01-02',medications = '[Seresta 10mg]'}, " +
            "MedicalrecordsItem{allergies = '[python]',firstName = 'Edouard',lastName = 'Lui'," +
            "birthdate = '1990-11-18',medications = '[Seresta 10mg]'}]";
    assertEquals(result,
            originalMedicalRecordService.deleteOriginalMedicalRecord(originalMedicalrecord4,
                    originalMedicalRecords).toString());
  }

  @Test
  void isMedicalRecordAlreadyInFile() {
    assertTrue(originalMedicalRecordService.isMedicalRecordAlreadyInFile("geff", "mwa",
            originalMedicalRecords));
  }

  @Test
  void isMedicalRecordAlreadyInFileFalse() {
    assertFalse(originalMedicalRecordService.isMedicalRecordAlreadyInFile("geff", "twa",
            originalMedicalRecords));
  }

  @Test
  void getMedicalRecordByFirstLastName() {
    String result = "MedicalrecordsItem{allergies = '[python]',firstName = 'Geff',lastName = " +
            "'Mwa',birthdate = '1984-08-23',medications = '[Seresta 10mg]'}";
    assertEquals(result,
            originalMedicalRecordService.getMedicalRecordByFirstLastName(originalMedicalRecords,
                    "geff", "mwa").toString());
  }

  @Test
  void getMedicalRecordByFirstLastNameNotFound() {
    String result = "MedicalrecordsItem{allergies = 'null',firstName = 'null',lastName = 'null'," +
            "birthdate = 'null',medications = 'null'}";
    assertEquals(result,
            originalMedicalRecordService.getMedicalRecordByFirstLastName(originalMedicalRecords,
                    "geff", "twa").toString());
  }

  @Test
  void getOriginalMedicalRecordHashMap() {
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertTrue(!originalMedicalRecordService.getOriginalMedicalRecordHashMap().isEmpty());
  }
}