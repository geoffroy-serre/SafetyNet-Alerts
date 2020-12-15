package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface WorkingPersonsService {


  ArrayList<WorkingPerson> createWorkingPersonsArrayList();

  ArrayList<WorkingPerson> reestablishCase(Collection<WorkingPerson> workingPersons);

  HashMap<String,WorkingPerson> getWorkingPersonsHashMap();
}
