package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.model.OutPutMedicalRecord;
import com.safetynet.alerts.model.OutPutResponse;
import com.safetynet.alerts.repository.RetrieveOutPutDataRepositoryImpl;
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
class OutPutMedicalRecordServiceImplTest {

  @Mock
  RetrieveOutPutDataRepositoryImpl retrieveOutPutDataRepository;

  @InjectMocks
  OutPutMedicalRecordServiceImpl outPutMedicalRecordService;

  ArrayList<OutPutMedicalRecord> outPutMedicalRecords = new ArrayList<>();
  ArrayList<String> allergies = new ArrayList<>();
  ArrayList<String> medications = new ArrayList<>();
  ArrayList<String> allergies2 = new ArrayList<>();
  ArrayList<String> medications2 = new ArrayList<>();
  ArrayList<String> allergies3 = new ArrayList<>();
  ArrayList<String> medications3 = new ArrayList<>();
  OutPutMedicalRecord outPutMedicalRecord = new OutPutMedicalRecord();
  OutPutMedicalRecord outPutMedicalRecord2 = new OutPutMedicalRecord();
  OutPutMedicalRecord outPutMedicalRecord3 = new OutPutMedicalRecord();

  OutPutResponse outPutResponse = new OutPutResponse();

  @BeforeEach
  void setup() {
    allergies.add("Python");
    medications.add("Java");
    outPutMedicalRecord.setIdMedicalRecord(UUID.randomUUID());
    outPutMedicalRecord.setMedications(allergies);
    outPutMedicalRecord.setAllergies(medications);

    allergies2.add("People");
    medications2.add("Computer");
    outPutMedicalRecord2.setIdMedicalRecord(UUID.randomUUID());
    outPutMedicalRecord2.setMedications(allergies2);
    outPutMedicalRecord2.setAllergies(medications2);

    allergies3.add("Gluten");
    medications3.add("Fruits");
    outPutMedicalRecord3.setIdMedicalRecord(UUID.randomUUID());
    outPutMedicalRecord3.setMedications(allergies3);
    outPutMedicalRecord3.setAllergies(medications3);

    outPutMedicalRecords.add(outPutMedicalRecord);
    outPutMedicalRecords.add(outPutMedicalRecord2);
    outPutMedicalRecords.add(outPutMedicalRecord3);
    outPutResponse.setMedicalrecords(outPutMedicalRecords);
  }

  @Test
  void getMedicalRecordById() {
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertEquals(outPutMedicalRecord.getMedications(),
            outPutMedicalRecordService.getMedicalRecordById(outPutMedicalRecord.getIdMedicalRecord()).getMedications());
    assertEquals(outPutMedicalRecord.getAllergies(),
            outPutMedicalRecordService.getMedicalRecordById(outPutMedicalRecord.getIdMedicalRecord()).getAllergies());
    assertEquals(outPutMedicalRecord2.getMedications(),
            outPutMedicalRecordService.getMedicalRecordById(outPutMedicalRecord2.getIdMedicalRecord()).getMedications());
    assertEquals(outPutMedicalRecord2.getAllergies(),
            outPutMedicalRecordService.getMedicalRecordById(outPutMedicalRecord2.getIdMedicalRecord()).getAllergies());
    assertEquals(outPutMedicalRecord3.getMedications(),
            outPutMedicalRecordService.getMedicalRecordById(outPutMedicalRecord3.getIdMedicalRecord()).getMedications());
    assertEquals(outPutMedicalRecord3.getAllergies(),
            outPutMedicalRecordService.getMedicalRecordById(outPutMedicalRecord3.getIdMedicalRecord()).getAllergies());

  }

  @Test
  void getAllMedicalRecords() {
    ArrayList<OutPutMedicalRecord> outPutMedicalRecordsTest = new ArrayList<>();
    OutPutMedicalRecord outPutMedicalRecordTest = new OutPutMedicalRecord();
    outPutMedicalRecordsTest.add(outPutMedicalRecordTest);
    OutPutResponse outPutResponseTest = new OutPutResponse();
    outPutResponseTest.setMedicalrecords(outPutMedicalRecords);
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponseTest);
    assertNotNull(outPutMedicalRecordService.getAllMedicalRecords());
    assertDoesNotThrow(() -> outPutMedicalRecordService.getAllMedicalRecords());

  }
}