package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface WorkingPersonsService {

  /**
   * from lower case to upper case for each first letter .
   *
   * @param workingPersons Collection<WorkingPerson>
   * @return ArrayList WorkingPerson
   */
  ArrayList<WorkingPerson> reestablishCase(Collection<WorkingPerson> workingPersons);

  /**
   * Key : firstname, lastname, adress,city,zipcode.
   * Value : WorkingPerson
   *
   * @return HashMap  of String WorkingPerson
   */
  HashMap<String, WorkingPerson> getWorkingPersonsHashMap();
}
