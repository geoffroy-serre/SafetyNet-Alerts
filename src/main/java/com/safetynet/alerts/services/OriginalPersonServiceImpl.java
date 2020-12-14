package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalPersonsService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.OutPutPerson;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalPersonServiceImpl implements OriginalPersonsService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @Override
  public ArrayList<OriginalPerson> postNewPerson(OriginalPerson originalPerson,
                                                 ArrayList<OriginalPerson> originalPersons) {
    boolean isPresent = false;
    for (OriginalPerson currentPerson : originalPersons) {
      if (originalPerson.getFirstName().equals(currentPerson.getFirstName()) &&
              originalPerson.getLastName().equals(originalPerson.getLastName())) {
        isPresent = true;
      }
    }
    if (isPresent) {
      return new ArrayList<OriginalPerson>();
    }
    ArrayList<OriginalPerson> originalPersonsResult = originalPersons;
    originalPersonsResult.add(originalPerson);
    return originalPersonsResult;
  }


  @Override
  public ArrayList<OriginalPerson> getOriginalPersons(OriginalResponse originalResponse) {
    return originalResponse.getPersons();
  }

  @Override
  public HashMap<String, OriginalPerson> getOriginalPersonHashMap() {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, OriginalPerson> originalPersonHashMap = new HashMap<>();

    for (OriginalPerson originalPersons : originalResponse.getPersons()) {
      originalPersonHashMap.put(originalPersons.getFirstName() + "," + originalPersons.getLastName(),
              originalPersons);
    }
    return originalPersonHashMap;
  }

  @Override
  public OriginalPerson getOriginalPersonByFirstAndLastName(String firstName, String lastName,
                                                            ArrayList<OriginalPerson> originalPersons) {

    for (OriginalPerson originalPerson : originalPersons) {
      if (originalPerson.getFirstName().equalsIgnoreCase(firstName) && originalPerson.getLastName().equalsIgnoreCase(lastName)) {
        return originalPerson;
      }
    }
    return new OriginalPerson();
  }

  @Override
  public OriginalPerson replacePersonData(OriginalPerson source) {
    OriginalPerson result = new OriginalPerson();
    BeanUtils.copyProperties(source, result);
    return result;
  }

  @Override
  public ArrayList<OriginalPerson> deleteOriginalPerson(OriginalPerson originalPerson,
                                                        ArrayList<OriginalPerson> originalPersons) {
    ArrayList<OriginalPerson> results = new ArrayList<>();
    for (OriginalPerson currentPerson : originalPersons) {
      if (!originalPerson.getFirstName().equals(currentPerson.getFirstName()) && !originalPerson.getLastName().equals(currentPerson.getLastName())) {
        results.add(currentPerson);
      }
    }
    return results;
  }

  @Override
  public ArrayList<OriginalPerson> deletePerson(String firstName, String lastName,
                                                ArrayList<OriginalPerson> originalPersons) {
    ArrayList<OriginalPerson> results = new ArrayList<>();
    for (OriginalPerson currentPerson : originalPersons) {
      if (!currentPerson.getFirstName().equals(firstName) && !currentPerson.getLastName().equals(lastName)) {
        results.add(currentPerson);
      }
    }
    return results;
  }


}
