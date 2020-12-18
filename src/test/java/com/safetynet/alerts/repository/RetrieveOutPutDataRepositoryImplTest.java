package com.safetynet.alerts.repository;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetrieveOutPutDataRepositoryImplTest {

RetrieveOutPutDataRepository retrieveOutPutDataRepository = new RetrieveOutPutDataRepositoryImpl();
  @Test
  void getOutPutDataWithWrongFile() {
    String file = "wrong";
    assertDoesNotThrow(() -> retrieveOutPutDataRepository.getOutPutData(file));
    assertNotNull(retrieveOutPutDataRepository.getOutPutData(file));

  }
  @Test
  void getOutPutData() {

    assertDoesNotThrow(() -> retrieveOutPutDataRepository.getOutPutData(FilesPath.ORIGINAL_INPUT_FILE));
    assertNotNull(retrieveOutPutDataRepository.getOutPutData(FilesPath.ORIGINAL_INPUT_FILE));

  }
}