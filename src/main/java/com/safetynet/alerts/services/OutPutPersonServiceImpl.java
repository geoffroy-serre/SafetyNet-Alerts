package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutPersonService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.WorkingHome;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPutPersonServiceImpl implements OutPutPersonService {
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

@Override
  public ArrayList<WorkingPerson> getAllPerson() {
    ArrayList<WorkingPerson> output = new ArrayList<>();
      ArrayList<WorkingPerson> workingPersons =
            retrieveOutPutDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE).getPersons();
    for (WorkingPerson current : workingPersons) {
      output.add(current);
    }
    return output;
  }
}
