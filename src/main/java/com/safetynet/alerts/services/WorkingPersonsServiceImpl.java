package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalPersons;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingPersonsServiceImpl implements WorkingPersonsService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  /**
   * ArrayList created from private Methode getWorkingPersonsHashSet
   * @return ArrayList<WorkingPerson>
   */
  public ArrayList<WorkingPerson> createWorkingPersonsArrayList(String filePath) {
    ArrayList<WorkingPerson> workingPersons = new ArrayList<WorkingPerson>();
    workingPersons.addAll(getWorkingPersonsHashSet(filePath));
    return workingPersons;

  }

  /**
   * Using hashset to get unique Person with lastname and firstname.
   * And birthdate, email, and phone.
   *
   * @return HashSet<WorkingPerson>
   */
  public HashSet<WorkingPerson> getWorkingPersonsHashSet(String filePath) {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(filePath);
    HashSet<WorkingPerson> workingPersonsHashSet = new HashSet<>();

    for (OriginalPersons originalPerson : originalResponse.getPersons()) {
      WorkingPerson workingPerson = new WorkingPerson();
      workingPerson.setId(UUID.randomUUID());
      workingPerson.setEmail(originalPerson.getEmail());
      workingPerson.setFirstName(originalPerson.getFirstName());
      workingPerson.setLastName(originalPerson.getLastName());
      workingPersonsHashSet.add(workingPerson);
    }
    return workingPersonsHashSet;
  }
}
