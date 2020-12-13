package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public interface OutPutHomeService {


  ArrayList<OutPutHome> setStationNumberNull(ArrayList<OutPutHome> outPutHomes);

  ArrayList<OutPutHome> getCountChildrenAndAdultsforList(ArrayList<OutPutHome> outPutHomes);

  ArrayList<OutPutHome> getHomeByStationNumber(ArrayList<OutPutHome> outPutHomes,
                                               OutPutFireStation firestation);

  ArrayList<OutPutHome> setPersons(ArrayList<OutPutPerson> persons,
                                   ArrayList<OutPutHome> homes);

  OutPutHome setPersonsHome(ArrayList<OutPutPerson> persons,
                            OutPutHome home);

  ArrayList<UUID> getHomesIds(ArrayList<OutPutHome> homes);

  ArrayList<OutPutHome> getHomesbyIds(ArrayList<OutPutFireStation> firestations,
                                      ArrayList<OutPutHome> homes);

  OutPutHome getHomeByAddress(String Address);

  HashSet<UUID> getHomesByCity(String city);

  OutPutHome transformWorkingIntoOutPut(WorkingHome inputHome);

  ArrayList<OutPutHome> getOutPutHomeList();

  ArrayList<OutPutHome> transformWorkingIntoOutPut(ArrayList<WorkingHome> inputHome);
}
