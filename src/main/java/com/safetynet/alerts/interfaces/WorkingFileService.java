package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.*;
import java.util.ArrayList;


public interface WorkingFileService {

  public WorkingResponse createWorkingFile();

  public ArrayList<WorkingPerson> createWorkingPersons();

  public ArrayList<WorkingHome> createWorkingHomes(Response originalResponse);

  public ArrayList<WorkingFireStation> createWorkingFireStations();

  public ArrayList<WorkingMedicalRecord> createWorkingMedicalRecords();
}
