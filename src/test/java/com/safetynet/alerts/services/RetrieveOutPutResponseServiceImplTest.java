package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutMedicalRecord;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.OutPutResponse;
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
class RetrieveOutPutResponseServiceImplTest {

  @Mock
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @InjectMocks
  RetrieveOutPutResponseServiceImpl retrieveOutPutResponseService;

  @Test
  void retrieveOutPutResponse() {
    OutPutResponse outPutResponse = new OutPutResponse();
    outPutResponse.setPersons(new ArrayList<OutPutPerson>());
    outPutResponse.setMedicalrecords(new ArrayList<OutPutMedicalRecord>());
    outPutResponse.setFirestations(new ArrayList<OutPutFireStation>());
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertDoesNotThrow(() -> retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE));
    assertNotNull(retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE));

  }
}