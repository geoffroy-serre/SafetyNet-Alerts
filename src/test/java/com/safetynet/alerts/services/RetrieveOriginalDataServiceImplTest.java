package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveOriginalDataServiceImplTest {

  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @InjectMocks
  RetrieveOriginalDataServiceImpl originalDataService;

  @Test
  void retrieveOriginalData() {
    OriginalResponse originalResponse = new OriginalResponse();
    originalResponse.setPersons(new ArrayList<OriginalPerson>());
    originalResponse.setMedicalrecords(new ArrayList<OriginalMedicalrecord>());
    originalResponse.setFirestations(new ArrayList<OriginalFirestation>());
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertDoesNotThrow(() -> originalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE));
    assertNotNull(originalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE));
  }
}