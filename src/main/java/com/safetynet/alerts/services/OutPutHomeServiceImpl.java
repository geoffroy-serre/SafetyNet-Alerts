package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.WorkingHome;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OutPutHomeServiceImpl implements OutPutHomeService {
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  public WorkingHome getHomeById(UUID homeId) {
    WorkingHome workingHome = new WorkingHome();
    ArrayList<WorkingHome> workingHomes =
            retrieveOutPutDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE).getHomes();
    for (WorkingHome currentWorkingHome : workingHomes) {
      if (currentWorkingHome.getIdHome().equals(homeId)) {
        workingHome = currentWorkingHome;
        return workingHome;
      }
    }
    return null;
  }
}
