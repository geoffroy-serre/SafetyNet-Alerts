package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingResponse;

public interface CreateWorkingFileService {
  void writeFile(WorkingResponse wr);

  void createWorkingFile();
}
