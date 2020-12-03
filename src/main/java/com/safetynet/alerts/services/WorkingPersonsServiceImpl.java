package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalPersons;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingPersonsServiceImpl implements WorkingPersonsService {
  @Autowired
  OriginalResponse originalResponse;

  ArrayList<WorkingPerson> workingPersons = new ArrayList<WorkingPerson>();

  public ArrayList<WorkingPerson> createWorkingPersons() {

    for (OriginalPersons originalPerson : originalResponse.getPersons()) {
      WorkingPerson workingPerson = new WorkingPerson();
      workingPerson.setId(UUID.randomUUID());
      workingPerson.setEmail(originalPerson.getEmail());
      workingPerson.setFirstName(originalPerson.getFirstName());
      workingPerson.setLastName(originalPerson.getLastName());
      workingPersons.add(workingPerson);
    }
    return workingPersons;
  }
}
