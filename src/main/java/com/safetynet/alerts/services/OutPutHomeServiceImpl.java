package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OutPutHomeServiceImpl implements OutPutHomeService {
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  public ArrayList<OutPutHome> setPersons(ArrayList<OutPutPerson> persons,
                                          ArrayList<OutPutHome> homes) {
    ArrayList<OutPutHome> result = new ArrayList<>();
    for (OutPutHome outPutHome : homes) {
      ArrayList<OutPutPerson> personeHome = new ArrayList<>();
      for (OutPutPerson outPutPerson : persons) {
        if (outPutHome.getIdHome().equals(outPutPerson.getIdHome())) {
          personeHome.add(outPutPerson);
        }
        outPutHome.setPersons(personeHome);
      }
      result.add(outPutHome);
    }

    return result;
  }

  @Override
  public ArrayList<UUID> getHomesIds(ArrayList<OutPutHome> homes) {
    ArrayList<UUID> result = new ArrayList<>();
    for (OutPutHome outPutHome : homes) {
      result.add(outPutHome.getIdHome());
    }
    return result;
  }

  @Override
  public ArrayList<OutPutHome> getHomesbyIds(ArrayList<OutPutFireStation> firestations,
                                             ArrayList<OutPutHome> homes) {
    ArrayList<OutPutHome> homesResult = new ArrayList<>();
    for (OutPutFireStation outPutFireStation : firestations) {
      for (UUID fireStationHomeId : outPutFireStation.getHomeListIds()) {
        for (OutPutHome outPutHome : homes) {
          if (outPutHome.getIdHome().equals(fireStationHomeId)) {
            outPutHome.setStationNumber(outPutFireStation.getStationNumber());
            homesResult.add(outPutHome);
          }
        }
      }
    }
    return homesResult;
  }

  @Override
  public OutPutHome getHomeByAddress(String address) {
    OutPutHome selectedHome = new OutPutHome();
    for (OutPutHome outPutHome : getOutPutHomeList()) {
      if (outPutHome.getAddress().equalsIgnoreCase(address)) {
        selectedHome = outPutHome;
      }
    }
    return selectedHome;
  }

  @Override
  public HashSet<UUID> getHomesByCity(String city) {
    HashSet<UUID> homesIds = new HashSet<>();

    for (OutPutHome outPutHome : getOutPutHomeList()) {
      if (outPutHome.getCity().equalsIgnoreCase(city)) {
        homesIds.add(outPutHome.getIdHome());
      }
    }
    return homesIds;
  }

  @Override
  public OutPutHome transformWorkingIntoOutPut(WorkingHome inputHome) {
    OutPutHome result = new OutPutHome();

    result.setAddress(inputHome.getAddress());
    result.setCity(inputHome.getCity());
    result.setZip(inputHome.getZip());

    return result;
  }

  @Override
  public ArrayList<OutPutHome> getOutPutHomeList() {
    ArrayList<OutPutHome> outPutHomes =
            retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getHomes();
    return outPutHomes;
  }

  @Override
  public ArrayList<OutPutHome> transformWorkingIntoOutPut(ArrayList<WorkingHome> inputHome) {
    ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
    for (WorkingHome current : inputHome) {
      OutPutHome result = new OutPutHome();
      result.setAddress(current.getAddress());
      result.setCity(current.getCity());
      result.setZip(current.getZip());

      outPutHomes.add(result);
    }
    return outPutHomes;
  }
}
