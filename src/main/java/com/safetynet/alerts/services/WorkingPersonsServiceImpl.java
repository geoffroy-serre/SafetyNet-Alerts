package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.HashMap;
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
   *
   * @return ArrayList<WorkingPerson>
   */
  @Override
  public ArrayList<WorkingPerson> createWorkingPersonsArrayList() {
    ArrayList<WorkingPerson> workingPersons = new ArrayList<WorkingPerson>();
    //workingPersons.addAll(getWorkingPersonsHashSet());
    return workingPersons;

  }

  /**
   * Using hashset to get unique Person with lastname and firstname.
   * And birthdate, email, and phone.
   *
   * @return HashSet<WorkingPerson>
   */
  @Override
  public HashMap<String,WorkingPerson> getWorkingPersonsHashMap() {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String,WorkingPerson> workingPersonsHashMap = new HashMap<>();

    for (OriginalPerson originalPerson : originalResponse.getPersons()) {
      WorkingPerson workingPerson = new WorkingPerson();
      workingPerson.setId(UUID.randomUUID());
      workingPerson.setEmail(originalPerson.getEmail());
      workingPerson.setFirstName(originalPerson.getFirstName());
      workingPerson.setLastName(originalPerson.getLastName());
      workingPerson.setPhone(originalPerson.getPhone());
      String createdKey =
              originalPerson.getFirstName()+","+originalPerson.getLastName()+","+originalPerson.getAddress()+","+originalPerson.getCity()+","+originalPerson.getZip();
      workingPersonsHashMap.put(createdKey,workingPerson);
    }
    return workingPersonsHashMap;
  }




}
