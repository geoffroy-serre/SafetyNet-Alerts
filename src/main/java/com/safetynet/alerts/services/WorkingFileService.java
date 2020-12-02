package com.safetynet.alerts.services;


import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalData;
import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFileService {
  @Autowired
  RetrieveOriginalData retrieveOriginalData;

  OriginalResponse originalResponse =
          retrieveOriginalData.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);

  public ArrayList<WorkingHome> createWorkingHomes(OriginalResponse originalResponse) {
    ArrayList<WorkingHome> workingHome = new ArrayList<WorkingHome>();
    for (OriginalPersons currentIteration : originalResponse.getPersons()) {
      WorkingHome addingHome = new WorkingHome();
      addingHome.setAddress(currentIteration.getAddress());
      addingHome.setCity(currentIteration.getCity());
      addingHome.setZip(currentIteration.getZip());
      addingHome.setIdHome(UUID.randomUUID());
      workingHome.add(addingHome);
    }
    return workingHome;
  }

  public ArrayList<WorkingFireStation> createWorkingFireStations() {
    ArrayList<WorkingFireStation> workingFireStations = new ArrayList<WorkingFireStation>();
    ArrayList<OriginalFirestation> originalFirestations = originalResponse.getFirestations();
    return null;
  }

  public WorkingResponse createWorkingFile(String inputFile) {
    WorkingResponse workingResponse = new WorkingResponse();
    workingResponse.setPersons(createWorkingPersons());
    workingResponse.setFirestations(createWorkingFireStations());
    workingResponse.setMedicalrecords(createWorkingMedicalRecords());
    return workingResponse;
  }

  public ArrayList<WorkingPerson> createWorkingPersons() {
    ArrayList<WorkingPerson> workingPersons = new ArrayList<WorkingPerson>();
    for (OriginalPersons originalPerson : originalResponse.getPersons()) {
      WorkingPerson workingPerson = new WorkingPerson();
      workingPerson.setId(UUID.randomUUID());
      workingPerson.setEmail(originalPerson.getEmail());
      workingPerson.setFirstName(originalPerson.getFirstName());
      workingPerson.setLastName(originalPerson.getLastName());

    }
    return null;
  }


  public ArrayList<WorkingMedicalRecord> createWorkingMedicalRecords() {
    return null;
  }
}
