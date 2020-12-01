package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.DataType;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.Response;
import com.safetynet.alerts.interfaces.RetrieveData;
import com.safetynet.alerts.interfaces.WorkingFileService;
import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFileServiceImpl implements WorkingFileService {


  public WorkingResponse createWorkingFile() {
    return null;
  }

  public ArrayList<WorkingPerson> createWorkingPersons() {
    ArrayList<OriginalPersons> originalPersons = new ArrayList<OriginalPersons>();

    //ArrayList<WorkingPerson> work = new ArrayList<WorkingPerson>(workingPersons);

    return null;
  }

  public ArrayList<WorkingHome> createWorkingHomes(Response originalResponse) {
    ArrayList<WorkingHome> workingHome = new ArrayList<WorkingHome>();
    for (OriginalPersons currentIteration : originalResponse.getPersons()) {
      WorkingHome addingHome = new WorkingHome();

      addingHome.setAddress(currentIteration.g);
      addingHome.setCity(originalPersons.getCity());
      addingHome.setZip(originalPersons.getZip());
      addingHome.setIdHome(UUID.randomUUID());
      workingHome.add(addingHome);
    }
    return workingHome;
  }

  public ArrayList<WorkingFireStation> createWorkingFireStations() {
    return null;
  }

  public ArrayList<WorkingMedicalRecord> createWorkingMedicalRecords() {
    return null;
  }
}
