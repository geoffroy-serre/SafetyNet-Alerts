package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.HashSet;

public interface WorkingPersonsService {
  ArrayList<WorkingPerson> createWorkingPersonsArrayList(String filePath);
  HashSet<WorkingPerson> getWorkingPersonsHashSet(String filePath);
}
