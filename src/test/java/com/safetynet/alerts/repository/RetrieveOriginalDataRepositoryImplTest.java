package com.safetynet.alerts.repository;


import com.safetynet.alerts.constants.FilesPath;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


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