package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.HashMap;

public interface WorkingPersonsService {


  ArrayList<WorkingPerson> createWorkingPersonsArrayList();

  HashMap<String,WorkingPerson> getWorkingPersonsHashMap();
}
