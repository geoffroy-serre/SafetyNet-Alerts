package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import java.util.HashMap;

public interface OriginalPersonsService {
  ArrayList<OriginalPerson> postNewPerson(OriginalPerson originalPerson,
                                          ArrayList<OriginalPerson> originalPersons);

  ArrayList<OriginalPerson> getOriginalPersons (OriginalResponse originalResponse);

  HashMap<String, OriginalPerson> getOriginalPersonHashMap();

  OriginalPerson getOriginalPersonByFirstAndLastName(String firstName, String lastName,
                                                     ArrayList<OriginalPerson> originalPersons);

  OriginalPerson replacePersonData(OriginalPerson source);

  ArrayList<OriginalPerson> deleteOriginalPerson(OriginalPerson originalPerson,
                                                 ArrayList<OriginalPerson> originalPersons);

  ArrayList<OriginalPerson> deletePerson(String firstName, String lastName,
                                         ArrayList<OriginalPerson> originalPersons);
}
