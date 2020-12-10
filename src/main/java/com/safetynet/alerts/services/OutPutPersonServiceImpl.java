package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutPersonService;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPutPersonServiceImpl implements OutPutPersonService {
  @Autowired
  RetrieveWorkingDataRepository retrieveOutPutDataRepository;

  @Override
  public ArrayList<WorkingPerson> getAllPerson() {
    ArrayList<WorkingPerson> output = new ArrayList<>();
    ArrayList<WorkingPerson> workingPersons =
            retrieveOutPutDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE).getPersons();
    for (WorkingPerson current : workingPersons) {
      output.add(current);
    }
    return output;
  }

  @Override
  public OutPutPerson transformWorkingIntoOutPut(WorkingPerson inputPerson) {
    OutPutPerson result = new OutPutPerson();

    result.setPhone(inputPerson.getPhone());
    result.setAge(inputPerson.getAge());
    result.setEmail(inputPerson.getEmail());
    result.setFirstName(inputPerson.getFirstName());
    result.setLastName(inputPerson.getLastName());
    result.setBirthdate(inputPerson.getBirthdate());

    return result;


  }

  @Override
  public ArrayList<OutPutPerson> transformWorkingIntoOutPut(ArrayList<WorkingPerson> inputPersons) {
    ArrayList<OutPutPerson> output = new ArrayList<>();
    for (WorkingPerson wp : inputPersons){
      OutPutPerson result = new OutPutPerson();
      result.setPhone(wp.getPhone());
      result.setAge(wp.getAge());
      result.setEmail(wp.getEmail());
      result.setFirstName(wp.getFirstName());
      result.setLastName(wp.getLastName());
      result.setBirthdate(wp.getBirthdate());
      output.add(result);
    }
    return output;
    }
}
