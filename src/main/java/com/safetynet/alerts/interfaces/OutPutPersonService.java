package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public interface OutPutPersonService {


  boolean isPersonAlreadyInFile(String firstName, String lastName);

  ArrayList<OutPutPerson> setEmailToNull(ArrayList<OutPutPerson> persons);

  ArrayList<OutPutPerson> getPersonByHomeIds(ArrayList<UUID> homeIds);

  HashSet<String> getPersonsPhones(ArrayList<OutPutPerson> persons);

  ArrayList<OutPutPerson> setPersonsHome(ArrayList<OutPutPerson> persons,
                                         ArrayList<OutPutHome> homes);

  ArrayList<OutPutPerson> setMedicalRecordForPersons(ArrayList<OutPutPerson> outPutPersons
          , ArrayList<OutPutMedicalRecord> outPutMedicalRecords);

  ArrayList<OutPutPerson> setPhoneNull(ArrayList<OutPutPerson> persons);

  ArrayList<OutPutPerson> getPersonsByFirstAndLastName(String firstName, String lastName);

  HashSet<String> getPersonsEmailByCity(HashSet<UUID> homeIds);

  OutPutChild getCountedTypeOfPersons(ArrayList<OutPutPerson> persons);

  ArrayList<OutPutPerson> getPersonsByHomeID(OutPutHome outPutHome);

  ArrayList<OutPutPerson> getAllPerson();

  OutPutPerson transformWorkingIntoOutPut(WorkingPerson inputPerson);

  ArrayList<OutPutPerson> transformWorkingIntoOutPut(ArrayList<WorkingPerson> inputPerson);
}
