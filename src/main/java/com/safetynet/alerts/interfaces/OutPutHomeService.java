package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public interface OutPutHomeService {

  /**
   * Set StationNumber to null to avoir their display.
   *
   * @param outPutHomes ArrayList<OutPutHome>
   * @return ArrayList of OutPutHome
   */
  ArrayList<OutPutHome> setStationNumberNull(ArrayList<OutPutHome> outPutHomes);

  /**
   * Count number of children and adult for given home.
   * Return empty List if no one home.
   *
   * @param outPutHomes ArrayList<OutPutHome>
   * @return ArrayList of OutPutHome
   */
  ArrayList<OutPutHome> getCountChildrenAndAdultsforList(ArrayList<OutPutHome> outPutHomes);

  /**
   * Retrieve home matching station number in given list
   *
   * @param outPutHomes ArrayList<OutPutHome>
   * @param firestation OutPutFireStation
   * @return ArrayList of OutPutHome
   */
  ArrayList<OutPutHome> getHomeByStationNumber(ArrayList<OutPutHome> outPutHomes,
                                               OutPutFireStation firestation);

  /**
   * Set persons data for given Homes.
   *
   * @param persons ArrayList<OutPutPerson>
   * @param homes   ArrayList<OutPutHome>
   * @return ArrayList of OutPutHome
   */
  ArrayList<OutPutHome> setPersons(ArrayList<OutPutPerson> persons,
                                   ArrayList<OutPutHome> homes);

  /**
   * Set persons data for given home.
   *
   * @param persons ArrayList<OutPutPerson>
   * @param home    OutPutHome
   * @return OutPutHome
   */
  OutPutHome setPersonsHome(ArrayList<OutPutPerson> persons,
                            OutPutHome home);

  /**
   * @param homes ArrayList<OutPutHome>
   * @return ArrayList of UUID
   */
  ArrayList<UUID> getHomesIds(ArrayList<OutPutHome> homes);

  /**
   * @param firestations ArrayList<OutPutFireStation>
   * @param homes        ArrayList<OutPutHome>
   * @return ArrayList of OutPutHome
   */
  ArrayList<OutPutHome> getHomesbyIds(ArrayList<OutPutFireStation> firestations,
                                      ArrayList<OutPutHome> homes);

  /**
   * Return new OutPutHome if no home for this address.
   *
   * @param address String
   * @return OutPutHome
   */
  OutPutHome getHomeByAddress(String address);

  /**
   * Return empty HashSet if no matches.
   *
   * @param city String
   * @return HashSet of UUID
   */
  HashSet<UUID> getHomesByCity(String city);

  /**
   * @param inputHome WorkingHome
   * @return
   */
  OutPutHome transformWorkingIntoOutPut(WorkingHome inputHome);

  /**
   * @return ArrayList of OutPutHome
   */
  ArrayList<OutPutHome> getOutPutHomeList();

  /**
   * Convertion of Homes
   *
   * @param inputHome ArrayList<OutPutHome>
   * @return ArrayList of OutPutHome
   */
  ArrayList<OutPutHome> transformWorkingIntoOutPut(ArrayList<WorkingHome> inputHome);
}
