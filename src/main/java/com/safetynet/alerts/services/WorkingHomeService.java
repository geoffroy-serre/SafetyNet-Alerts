package com.safetynet.alerts.services;

import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * The Class HomeService.
 */
@Service
public class WorkingHomeService {

  /**
   * Return Object Working Home found by equality between
   * address parameter and given list occurrence.
   *
   * @param address String
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

}
