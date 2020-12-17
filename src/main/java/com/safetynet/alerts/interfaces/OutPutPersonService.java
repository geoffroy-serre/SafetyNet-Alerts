package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public interface OutPutPersonService {

  /**
   * Check for presence of a persons in workingFile
   *
   * @param firstName
   * @param lastName
   * @return boolean
   */
  boolean isPersonAlreadyInFile(String firstName, String lastName);

  /**
   * Set email to null to avoid diplay on result
   *
   * @param persons
   * @return ArrayLsit of OutPutPerson
   */
  ArrayList<OutPutPerson> setEmailToNull(ArrayList<OutPutPerson> persons);

  /**
   * @param homeIds
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> getPersonByHomeIds(ArrayList<UUID> homeIds);

  /**
   * @param persons
   * @return HashSet of String
   */
  HashSet<String> getPersonsPhones(ArrayList<OutPutPerson> persons);

  /**
   * Set Persons Homes data
   *
   * @param persons
   * @param homes
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> setPersonsHome(ArrayList<OutPutPerson> persons,
                                         ArrayList<OutPutHome> homes);

  /**
   * Set medicalrecord data for OutPutPersons
   *
   * @param outPutPersons
   * @param outPutMedicalRecords
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> setMedicalRecordForPersons(ArrayList<OutPutPerson> outPutPersons
          , ArrayList<OutPutMedicalRecord> outPutMedicalRecords);

  /**
   * Set Phone null to avoi display
   *
   * @param persons
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> setPhoneNull(ArrayList<OutPutPerson> persons);

  /**
   * Search for Person with given parameters
   * Not case sensitive
   *
   * @param firstName
   * @param lastName
   * @return
   */
  ArrayList<OutPutPerson> getPersonsByFirstAndLastName(String firstName, String lastName);

  /**
   * List in parameter already contains right person with right city
   *
   * @param homeIds
   * @return HashSet of String
   */
  HashSet<String> getPersonsEmailByCity(HashSet<UUID> homeIds);

  /**
   * Create OutPutChild with a list for adult a list for children.
   * Depending of constant Of_Age
   *
   * @param persons
   * @return OutPutChild
   */
  OutPutChild getCountedTypeOfPersons(ArrayList<OutPutPerson> persons);

  /**
   * @param outPutHome
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> getPersonsByHomeID(OutPutHome outPutHome);

  /**
   * Get All OutPutPerson from working File
   *
   * @return ArrayLsit of OutPutPerson
   */
  ArrayList<OutPutPerson> getAllPerson();

  /**
   * Person conversion.
   *
   * @param inputPerson
   * @return OutPutPerson
   */
  OutPutPerson transformWorkingIntoOutPut(WorkingPerson inputPerson);

  /**
   * Persons conversion
   *
   * @param inputPerson
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> transformWorkingIntoOutPut(ArrayList<WorkingPerson> inputPerson);
}
