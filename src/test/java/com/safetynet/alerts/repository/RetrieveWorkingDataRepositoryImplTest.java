package com.safetynet.alerts.repository;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RetrieveWorkingDataRepositoryImplTest {


  RetrieveWorkingDataRepository retrieveWorkingDataRepository =
          new RetrieveWorkingDataRepositoryImpl();

  @Test
  void getWorkingDataWithWrongFile() {
    String file = "wrong";
    assertDoesNotThrow(() -> retrieveWorkingDataRepository.getWorkingData(file));
    assertNotNull(retrieveWorkingDataRepository.getWorkingData(file));

  }

  @Test
  void getWorkingData() {

    assertDoesNotThrow(() -> retrieveWorkingDataRepository.getWorkingData(FilesPath.ORIGINAL_INPUT_FILE));
    assertNotNull(retrieveWorkingDataRepository.getWorkingData(FilesPath.ORIGINAL_INPUT_FILE));

  }

}