package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.repository.RetrieveOriginalDataRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OriginalFileServiceImplTest {
  @Mock
  RetrieveOriginalDataRepository retrieveOriginalData;
  @Mock
  OriginalResponse originalResponse;

  @InjectMocks
  OriginalFileServiceImpl originalFileService;


  @Test
  void getOriginalResponseWithRightFile() {
    when(retrieveOriginalData.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(new OriginalResponse());
    assertDoesNotThrow(() ->  originalFileService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE));
    assertNotNull(originalFileService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE));
  }

  @Test
  void getOriginalResponseWithWrongFile() {
    when(retrieveOriginalData.getOriginalData("wrongFile")).thenReturn(new OriginalResponse());
    assertDoesNotThrow(() ->  originalFileService.getOriginalResponse("wrongFile"));
    assertNotNull(originalFileService.getOriginalResponse("wrongFile"));
  }


}