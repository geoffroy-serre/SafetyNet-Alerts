package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public interface OutPutPersonService {

  /**
   * Check for presence of a persons in workingFile.
   *
   * @param firstName String
   * @param lastName  String
   * @return boolean
   */
  boolean isPersonAlreadyInFile(String firstName, String lastName);

  /**
   * Set email to null to avoid diplay on result.
   *
   * @param persons ArrayList<OutPutPerson>
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> setEmailToNull(ArrayList<OutPutPerson> persons);

  /**
   * Get persons homd Ids.
   *
   * @param homeIds ArrayList<UUID>
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> getPersonByHomeIds(ArrayList<UUID> homeIds);

  /**
   * @param persons ArrayList<OutPutPerson>
   * @return HashSet of String
   */
  HashSet<String> getPersonsPhones(ArrayList<OutPutPerson> persons);

  /**
   * Set Persons Homes data.
   *
   * @param persons ArrayList<OutPutPerson>
   * @param homes   ArrayList<OutPutHome>
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> setPersonsHome(ArrayList<OutPutPerson> persons,
                                         ArrayList<OutPutHome> homes);

  /**
   * Set medicalrecord data for OutPutPersons.
   *
   * @param outPutPersons        ArrayList<OutPutPerson>
   * @param outPutMedicalRecords ArrayList<OutPutMedicalRecord>
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> setMedicalRecordForPersons(ArrayList<OutPutPerson> outPutPersons
          , ArrayList<OutPutMedicalRecord> outPutMedicalRecords);

  /**
   * Set Phone null to avoi display.
   *
   * @param persons ArrayList<OutPutPerson>
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> setPhoneNull(ArrayList<OutPutPerson> persons);

  /**
   * Search for Person with given parameters.
   * Not case sensitive
   *
   * @param firstName String
   * @param lastName  String
   * @return
   */
  ArrayList<OutPutPerson> getPersonsByFirstAndLastName(String firstName, String lastName);

  /**
   * List in parameter already contains right person with right city
   *
   * @param homeIds HashSet<UUID>
   * @return HashSet of String
   */
  HashSet<String> getPersonsEmailByCity(HashSet<UUID> homeIds);

  /**
   * Create OutPutChild with a list for adult a list for children.
   * Depending of constant Of_Age
   *
   * @param persons ArrayList<OutPutPerson>
   * @return OutPutChild
   */
  OutPutChild getCountedTypeOfPersons(ArrayList<OutPutPerson> persons);

  /**
   * @param outPutHome OutPutHome
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
   * @param inputPerson WorkingPerson
   * @return OutPutPerson
   */
  OutPutPerson transformWorkingIntoOutPut(WorkingPerson inputPerson);

  /**
   * Persons conversion
   *
   * @param inputPerson ArrayList<WorkingPerson>
   * @return ArrayList of OutPutPerson
   */
  ArrayList<OutPutPerson> transformWorkingIntoOutPut(ArrayList<WorkingPerson> inputPerson);
}
