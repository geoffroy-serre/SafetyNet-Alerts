package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;

public interface OutPutHomeService {
   OutPutHome transformWorkingIntoOutPut(WorkingHome inputHome);

  ArrayList<OutPutHome> getOutPutHomeList();

  ArrayList<OutPutHome> transformWorkingIntoOutPut(ArrayList<WorkingHome> inputHome);
}
