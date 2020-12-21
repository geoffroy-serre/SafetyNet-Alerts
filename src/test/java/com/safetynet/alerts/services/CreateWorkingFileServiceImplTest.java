package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CreateWorkingFileServiceImplTest {
  @Mock
  RetrieveOriginalDataService retrieveOriginalDataService;
  @Mock
  WorkingHomeService workingHomeService;
  @Mock
  OriginalResponse originalResponse;
  @Mock
  WorkingPersonsService workingPersonsService;
  @Mock
  WorkingFirestationsService workingFireStationService;
  @Mock
  WorkingMedicalRecordService workingMedicalRecordService;
  @Mock
  OriginalPersonsService originalPersonsService;
  @Mock
  OriginalMedicalRecordService originalMedicalRecordService;
  @Mock
  OriginalFireStationService originalFireStationService;

  @InjectMocks
  CreateWorkingFileServiceImpl createWorkingFileService;

  @Test
  void writeFile() {
    WorkingResponse wr = new WorkingResponse();
    assertDoesNotThrow(() -> createWorkingFileService.writeFile(wr));

  }

  @Test
  void createWorkingFile() {
    WorkingResponse wr = new WorkingResponse();
    assertDoesNotThrow(() -> createWorkingFileService.createWorkingFile());

  }
}