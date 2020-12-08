package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.HashSet;

public interface OutPutPersonService {

  ArrayList<WorkingPerson> getAllPerson();

  OutPutPerson transformWorkingIntoOutPut(WorkingPerson inputPerson);
}
