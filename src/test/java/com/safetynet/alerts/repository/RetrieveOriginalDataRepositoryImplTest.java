package com.safetynet.alerts.repository;


import com.safetynet.alerts.constants.FilesPath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class RetrieveOriginalDataRepositoryImplTest {



RetrieveOriginalDataRepositoryImpl retrieveOriginalDataRepository =
        new RetrieveOriginalDataRepositoryImpl();

  @Test
  void getOriginalDataWithUnknownFile() {
    String file = "wrong";
    assertDoesNotThrow(() -> retrieveOriginalDataRepository.getOriginalData(file));
    assertNotNull(retrieveOriginalDataRepository.getOriginalData(file));
  }
  @Test
  void getOriginalDataWithKnownFile() {

    assertDoesNotThrow(() -> retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE));
    assertNotNull(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE));
  }
}