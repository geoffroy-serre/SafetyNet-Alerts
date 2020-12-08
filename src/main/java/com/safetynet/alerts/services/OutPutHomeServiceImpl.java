package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.WorkingHome;
import com.safetynet.alerts.model.WorkingPerson;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OutPutHomeServiceImpl implements OutPutHomeService {
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  public OutPutHome transformWorkingIntoOutPut(WorkingHome inputHome) {
    OutPutHome result = new OutPutHome();

    result.setAddress(inputHome.getAddress());
    result.setCity(inputHome.getCity());
    result.setZip(inputHome.getZip());

    return result;
  }

  @Override
  public ArrayList<OutPutHome> getOutPutHomeList(){
    ArrayList<WorkingHome> workingHomes =
            retrieveOutPutDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE).getHomes();
    ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
    for (WorkingHome workingHome : workingHomes){
      outPutHomes.add(transformWorkingIntoOutPut(workingHome));
    }
    return outPutHomes;
  }

  @Override
  public ArrayList<OutPutHome> transformWorkingIntoOutPut(ArrayList<WorkingHome> inputHome) {
    ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
    for(WorkingHome current : inputHome){
      OutPutHome result = new OutPutHome();
      result.setAddress(current.getAddress());
      result.setCity(current.getCity());
      result.setZip(current.getZip());
      outPutHomes.add(result);
    }
    return outPutHomes;
  }
}
