package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.OriginalPersons;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * The Class HomeService.
 */
@Service
public class WorkingHomeServiceImpl implements WorkingHomeService {

  ArrayList<WorkingHome> workingHomes = new ArrayList<WorkingHome>();

  /**
   * Return Object Working Home found by equality between
   * address parameter and given list occurrence.
   *
   * @param address      String
   * @param workingHomes ArrayList<WorkingHome>
   * @return WorkingHome
   */
  public WorkingHome searchWorkingHome(String address, ArrayList<WorkingHome> workingHomes) {
    for (WorkingHome workingHome : workingHomes) {
      if (workingHome.getAddress().equals(address)) {
        return workingHome;
      }
    }
    return null;

  }

  public ArrayList<WorkingHome> createWorkingHomes(OriginalResponse originalResponse) {

    for (OriginalPersons currentIteration : originalResponse.getPersons()) {
      WorkingHome addingHome = new WorkingHome();
      if (searchWorkingHome(currentIteration.getAddress(), workingHomes) == null) {
        addingHome.setAddress(currentIteration.getAddress());
        addingHome.setCity(currentIteration.getCity());
        addingHome.setZip(currentIteration.getZip());
        addingHome.setIdHome(UUID.randomUUID());
        workingHomes.add(addingHome);
      }

    }
    return workingHomes;
  }

}
