package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;

public interface OutPutPersonService {

  ArrayList<OutPutPerson> getAllPerson();

  OutPutPerson transformWorkingIntoOutPut(WorkingPerson inputPerson);

  ArrayList<OutPutPerson> transformWorkingIntoOutPut(ArrayList<WorkingPerson> inputPerson);
}
