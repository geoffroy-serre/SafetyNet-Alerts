package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingResponse;

public interface CreateWorkingFileService {
  /**
   * Write WorkingFile with provided WorkingResponse.
   *
   * @param wr WorkingResponse
   */
  void writeFile(WorkingResponse wr);

  /**
   * Create WorkingResponse and call writeFile() at the end.
   * From Original data, it create UUID wich link persons with home,
   * persons with medicalRecord, fireStation with Homes.
   * Is call at app startup.
   */
  void createWorkingFile();
}
