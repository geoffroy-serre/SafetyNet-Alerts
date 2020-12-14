package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import java.util.HashMap;

public interface OriginalPersonsService {
  /**
   * Verify if OriginalPerson exist in original file.
   * Then if it's not present, add the OriginalPerson to the
   *  existing list of OriginalPerson given in parameter.
   * @param originalPerson OriginalPerson
   * @param originalPersons ArrayList of OriginalPerson
   * @return ArrayList of OriginalPerson
   */
  ArrayList<OriginalPerson> postNewPerson(OriginalPerson originalPerson,
                                          ArrayList<OriginalPerson> originalPersons);

  /**
   * Get OriginalPersons from given OriginalResponse
   * @param originalResponse OriginalResponse
   * @return ArrayList of OriginalPerson>
   */
  ArrayList<OriginalPerson> getOriginalPersons (OriginalResponse originalResponse);

  /**
   * From original data file.
   * Set key String firstName + lastName
   * Set value OriginalPerson
   * @return HashMap  String, OriginalPerson
   */
  HashMap<String, OriginalPerson> getOriginalPersonHashMap();

  /**
   *Search for OriginalPerson matching provided firstName and lastName from the also provided
   * OriginalPerson List.
   * @param firstName String
   * @param lastName String
   * @param originalPersons ArrayList<OriginalPerson>
   * @return OriginalPerson
   */
  OriginalPerson getOriginalPersonByFirstAndLastName(String firstName, String lastName,
                                                     ArrayList<OriginalPerson> originalPersons);

  /**
   * Send an OriginalPerson created from OriginalPerson in parameter.
   * @param source OriginalPerson
   * @return OriginalPerson
   */
  OriginalPerson replacePersonData(OriginalPerson source);

  /**
   * Delete and OrginalPerson from a provided list of OriginalPerson.
   * @param originalPerson OriginalPerson
   * @param originalPersons ArrayList of OriginalPerson
   * @return ArrayList of OriginalPerson.
   */
  ArrayList<OriginalPerson> deleteOriginalPerson(OriginalPerson originalPerson,
                                                 ArrayList<OriginalPerson> originalPersons);

  /**
   * Search for OriginalPerson matching parameters in provided list.
   * Return a list without the OriginalPerson found.
   * If no matchs found return a list with same data provided.
   * @param firstName String
   * @param lastName String
   * @param originalPersons ArrayList of OriginalPerson
   * @return ArrayList of OriginalPerson
   */
  ArrayList<OriginalPerson> deletePerson(String firstName, String lastName,
                                         ArrayList<OriginalPerson> originalPersons);
}
